package com.crecrew.crecre.data.model.posts

data class Posts(
    val post_idx: Int,
    val board_idx: Int,
    val user_idx: Int,
    val title: String,
    val contents: String,
    val create_time: String,
    val update_time: String,
    val view_cnt: Int,
    val like_cnt: Int,
    val hate_cnt: Int,
    val is_anonymous: Int,
    val image_cnt: Int,
    val video_cnt: Int,
    val thumbnail_url: Any,
    val idx : Int,
    val name : String,
    val type : String,
    val creator_idx : Int,
    val reply_cnt : Int
)