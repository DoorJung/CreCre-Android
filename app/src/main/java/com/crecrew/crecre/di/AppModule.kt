package com.crecrew.crecre.di

import com.crecrew.crecre.data.local.pref.PreferenceManager
import com.crecrew.crecre.data.remote.api.NetworkService
import com.crecrew.crecre.ui.main.MainViewModel
import com.crecrew.crecre.util.scheduler.AndroidSchedulerProvider
import com.crecrew.crecre.util.scheduler.SchedulerProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val rxModule = module {
    //provide schedule provider
    factory<SchedulerProvider> { AndroidSchedulerProvider() }
}

val networkModule = module {
    single { NetworkService.create() }
}

val localModule = module {
    //SharedPreference
    single { PreferenceManager(get()) }
    //Room
}

val factoryModule = module {
}

val viewModule = module {

    viewModel { MainViewModel() }
}

val appModule = listOf(rxModule, networkModule, localModule, factoryModule, viewModule)