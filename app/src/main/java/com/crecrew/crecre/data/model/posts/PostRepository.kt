package com.crecrew.crecre.data.model.posts

import io.reactivex.Observable
import io.reactivex.Single

interface PostRepository {
    fun getTodayNewPosts(): Single<ArrayList<Posts>>
    fun getTodayHotPosts(): Single<ArrayList<Posts>>
    fun getNewPosts(): Single<ArrayList<Posts>>
    fun getHotPosts(): Single<ArrayList<Posts>>
    fun getAllNewPosts(): Observable<ArrayList<Posts>>
    fun getAllHotPosts(): Observable<ArrayList<Posts>>
    fun getHotPostsByBoardIdx(boardIdx: Int): Observable<ArrayList<Posts>>
    fun getPostsByBoardIdx(boardIdx: Int): Observable<ArrayList<Posts>>
    fun getAllPostsByBoardIdx(boardIdx: Int): Observable<ArrayList<Posts>>
}