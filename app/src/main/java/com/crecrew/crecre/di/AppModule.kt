package com.crecrew.crecre.di

import com.crecrew.crecre.data.local.pref.PreferenceManager
import com.crecrew.crecre.data.model.board.BoardRepository
import com.crecrew.crecre.data.model.board.BoardRepositoryImpl
import com.crecrew.crecre.data.model.posts.PostRepository
import com.crecrew.crecre.data.model.posts.PostRepositoryImpl
import com.crecrew.crecre.data.remote.api.NetworkService
import com.crecrew.crecre.ui.main.MainViewModel
import com.crecrew.crecre.ui.main.community.CommunityViewModel
import com.crecrew.crecre.ui.main.community.board.BoardViewModel
import com.crecrew.crecre.ui.main.community.communityBoard.CommunityBoardViewModel
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
    factory<BoardRepository> {
        BoardRepositoryImpl(
            get(), get()
        )
    }
    factory<PostRepository> {
        PostRepositoryImpl(
            get(), get()
        )
    }
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
    viewModel { CommunityViewModel(get(), get()) }
    viewModel { CommunityBoardViewModel(get(), get()) }
    viewModel { BoardViewModel(get(), get()) }
    //MyPage
    viewModel { MyPageViewModel(get()) }
}

val appModule = listOf(rxModule, networkModule, localModule, factoryModule, viewModule)