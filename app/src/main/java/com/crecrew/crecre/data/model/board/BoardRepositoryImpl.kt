package com.crecrew.crecre.data.model.board

import com.crecrew.crecre.data.local.pref.PreferenceManager
import com.crecrew.crecre.data.remote.api.NetworkService
import io.reactivex.Observable
import io.reactivex.Single

class BoardRepositoryImpl(val api: NetworkService, val pref: PreferenceManager) : BoardRepository {
    override fun getLikedBoards(): Observable<ArrayList<Board>>  = api
        .getLikedBoards(pref.findPreference("TOKEN", ""))
        .map { it.data }

    override fun getUnlikedBoards(): Observable<ArrayList<Board>> = api
        .getUnlikedBoards(pref.findPreference("TOKEN", ""))
        .map { it.data }
}