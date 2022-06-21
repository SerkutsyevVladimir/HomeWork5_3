package com.example.homework3.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.homework3.databinding.FragmentMapBinding
import com.example.homework3.domain.usecase.GetCountriesUseCase
import com.example.homework3.map.LocationService
import com.example.homework3.viewmodels.ListViewModel
import com.example.homework3.viewmodels.MapViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MapFragment : Fragment() {
    private var _binding: FragmentMapBinding? = null
    private val binding get() = requireNotNull(_binding) { "View was destroyed" }

    private val locationService by inject<LocationService>()

    private var googleMap: GoogleMap? = null
    private var locationListener: LocationSource.OnLocationChangedListener? = null

    private val getCountriesUseCase by inject<GetCountriesUseCase>()

    private val viewModel by viewModels<MapViewModel> {
        object : ViewModelProvider.Factory {
            @Suppress
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MapViewModel(
                    getCountriesUseCase
                ) as T
            }
        }
    }

    @SuppressLint("MissingPermission")
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { permissionGranted ->
        if (permissionGranted) {

            viewLifecycleOwner.lifecycleScope.launch {
                locationService.getCurrentLocation()?.let(::moveCameraToLocation)
            }

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentMapBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        locationService
            .getLocationFlow()
            .onEach {
                locationListener?.onLocationChanged(it)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        binding.mapView.getMapAsync { map ->
            googleMap = map.apply {
                uiSettings.isCompassEnabled = true
                uiSettings.isZoomControlsEnabled = true
                uiSettings.isMyLocationButtonEnabled = true
            }
            map.isMyLocationEnabled = hasLocationPermission()
            map.setLocationSource(object : LocationSource {
                override fun activate(listener: LocationSource.OnLocationChangedListener) {
                    locationListener = listener
                }

                override fun deactivate() {
                    locationListener = null
                }

            })

            viewModel.dataFlow
                .onEach {
                    it.onSuccess {  list ->
                        list.map{ country ->
                            map.addMarker(
                                MarkerOptions()
                                    .title(country.name)
                                    .position(
                                        LatLng(country?.latitude ?: 53.8980, country.longitude ?:  27.5827)
                                    ))
                        }
                    }.onFailure {
                        print(it)
                        Toast.makeText(requireContext(), it.message,Toast.LENGTH_LONG).show()
                    }
                }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
        binding.mapView.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.mapView.onDestroy()
        googleMap = null
        _binding = null
    }

    private fun moveCameraToLocation(location: Location) {
        val current = LatLng(location.latitude, location.longitude)
        googleMap?.animateCamera(
            CameraUpdateFactory.newLatLngZoom(current, 17f)
        )
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}