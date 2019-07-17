package com.crecrew.crecre.data.model.posts

import io.reactivex.Single

interface PostRepository {
    fun getTodayNewPosts(): Single<ArrayList<Posts>>
    fun getTodayHotPosts(): Single<ArrayList<Posts>>
    fun getNewPosts(): Single<ArrayList<Posts>>
    fun getHotPosts(): Single<ArrayList<Posts>>
}