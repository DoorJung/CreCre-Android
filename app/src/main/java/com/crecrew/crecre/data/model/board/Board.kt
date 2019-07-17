package com.crecrew.crecre.data.model.board

data class Board(
    val idx: Int,
    val name: String,
    val type: String,
    val creator_idx: Int,
    val is_love : Int,
    val userIdx : Int
)