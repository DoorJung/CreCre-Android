package com.crecrew.crecre.data.model.reply

data class Reply(
    val idx: Int,
    val post_idx: Int,
    val user_idx: Int,
    val content: String,
    val reply_create_time: String,
    val is_anonymous: Int,
    val name: String,
    val profile_url: String
)