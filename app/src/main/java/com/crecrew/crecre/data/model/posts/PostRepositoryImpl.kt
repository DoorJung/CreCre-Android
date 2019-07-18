package com.crecrew.crecre.data.model.posts

import com.crecrew.crecre.data.local.pref.PreferenceManager
import com.crecrew.crecre.data.remote.api.NetworkService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class PostRepositoryImpl(val api: NetworkService, val pref: PreferenceManager) : PostRepository {
    override fun getTodayNewPosts(): Single<ArrayList<Posts>> = api
        .getTodayNewPosts()
        .map { it.data }

    override fun getTodayHotPosts(): Single<ArrayList<Posts>> = api
        .getTodayHotPosts()
        .map { it.data }

    override fun getNewPosts(): Single<ArrayList<Posts>> = api
        .getNewPosts()
        .map { it.data }

    override fun getHotPosts(): Single<ArrayList<Posts>> = api
        .getHotPosts()
        .map { it.data }

    override fun getAllNewPosts(): Observable<ArrayList<Posts>> = api
        .getAllNewPosts()
        .map { it.data }

    override fun getAllHotPosts(): Observable<ArrayList<Posts>> = api
        .getAllHotPosts()
        .map { it.data }

    override fun getHotPostsByBoardIdx(boardIdx: Int): Observable<ArrayList<Posts>> = api
        .getHotPostsByBoardIdx(boardIdx)
        .map { it.data }

    override fun getPostsByBoardIdx(boardIdx: Int): Observable<ArrayList<Posts>> = api
        .getPostsByBoardIdx(boardIdx)
        .map { it.data }

    override fun getAllPostsByBoardIdx(boardIdx: Int): Observable<ArrayList<Posts>> =
        Observable.zip(
            api.getHotPostsByBoardIdx(boardIdx).map { it.data },
            api.getPostsByBoardIdx(boardIdx).map { it.data },
            BiFunction { t1, t2 -> ArrayList<Posts>().apply { addAll(t1); addAll(t2) } })
}