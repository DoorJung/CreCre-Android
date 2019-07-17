package com.crecrew.crecre.data.model.board

data class BoardResponse(
    val data: ArrayList<Board>,
    val message: String,
    val status: Int,
    val success: Boolean
)