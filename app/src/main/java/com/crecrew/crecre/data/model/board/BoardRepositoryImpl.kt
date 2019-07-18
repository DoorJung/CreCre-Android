package com.crecrew.crecre.data.model.board

import com.crecrew.crecre.data.local.pref.PreferenceManager
import com.crecrew.crecre.data.remote.api.NetworkService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class BoardRepositoryImpl(val api: NetworkService, val pref: PreferenceManager) : BoardRepository {
    override fun getLikedBoards(): Observable<ArrayList<Board>>  = api
        .getLikedBoards(pref.findPreference("TOKEN", ""))
        .map { it.data }

    override fun getUnlikedBoards(): Observable<ArrayList<Board>> = api
        .getUnlikedBoards(pref.findPreference("TOKEN", ""))
        .map { it.data }

    override fun getBoards(): Observable<ArrayList<Board>> =
        Observable.zip(
            api.getLikedBoards(pref.findPreference("TOKEN", "")).map { it.data },
            api.getUnlikedBoards(pref.findPreference("TOKEN", "")).map { it.data },
            BiFunction { t1, t2 -> ArrayList<Board>().apply { addAll(t1); addAll(t2) } })

    override fun likeBoard(boardIdx: Int): Single<Boolean>  = api
        .likeBoard(pref.findPreference("TOKEN", ""), boardIdx)
        .map { it.success }

    override fun cancelLikeBoard(boardIdx: Int): Single<Boolean> = api
        .cancelLikeBoard(pref.findPreference("TOKEN", ""), boardIdx)
        .map { it.success }
}