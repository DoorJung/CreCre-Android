package com.crecrew.crecre.data.model.posts

data class PostsResponse(
    val status: Int,
    val message: String,
    val success: Boolean,
    val data: ArrayList<Posts>
)