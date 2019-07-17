package com.crecrew.crecre.di

import com.crecrew.crecre.data.local.pref.PreferenceManager
import com.crecrew.crecre.data.remote.api.NetworkService
import com.crecrew.crecre.ui.main.MainViewModel
import com.crecrew.crecre.ui.main.community.CommunityViewModel
import com.crecrew.crecre.ui.main.home.HomeViewModel
import com.crecrew.crecre.ui.main.myPage.MyPageViewModel
import com.crecrew.crecre.ui.main.rank.RankViewModel
import com.crecrew.crecre.ui.main.vote.VoteViewModel
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
    //Main
    viewModel { MainViewModel(get()) }
    //Home
    viewModel { HomeViewModel(get()) }
    //Ranking
    viewModel { RankViewModel(get()) }
    //Vote
    viewModel { VoteViewModel(get()) }
    //Community
    viewModel { CommunityViewModel(get()) }
    //MyPage
    viewModel { MyPageViewModel(get()) }
}

val appModule = listOf(rxModule, networkModule, localModule, factoryModule, viewModule)