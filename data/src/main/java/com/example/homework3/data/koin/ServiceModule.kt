package com.example.homework3.data.koin

import com.example.homework3.data.service.NightModeServiceImpl
import com.example.homework3.domain.service.NightModeService
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val serviceModule = module {
    singleOf(::NightModeServiceImpl) {
        bind<NightModeService>()
    }
}