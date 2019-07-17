package com.crecrew.crecre.data.model.reply

data class ReplyResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: ArrayList<Reply>
)