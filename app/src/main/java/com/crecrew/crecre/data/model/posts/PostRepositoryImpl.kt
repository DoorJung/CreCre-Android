package com.crecrew.crecre.data.model.posts

import com.crecrew.crecre.data.local.pref.PreferenceManager
import com.crecrew.crecre.data.remote.api.NetworkService
import io.reactivex.Single

class PostRepositoryImpl(val api: NetworkService, val pref: PreferenceManager) : PostRepository {
    override fun getTodayNewPosts(): Single<ArrayList<Posts>>  = api
        .getTodayNewPosts()
        .map { it.data }

    override fun getTodayHotPosts(): Single<ArrayList<Posts>>  = api
        .getTodayHotPosts()
        .map { it.data }

    override fun getNewPosts(): Single<ArrayList<Posts>>  = api
        .getNewPosts()
        .map { it.data }

    override fun getHotPosts(): Single<ArrayList<Posts>>  = api
        .getHotPosts()
        .map { it.data }
}