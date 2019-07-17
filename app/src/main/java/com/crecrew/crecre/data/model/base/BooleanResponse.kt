package com.crecrew.crecre.data.model.base

data class BooleanResponse(
    val status: Int,
    val message: String,
    val data: Boolean
)