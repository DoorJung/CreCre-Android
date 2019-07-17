package com.crecrew.crecre.data.model.board

import io.reactivex.Observable
import io.reactivex.Single

interface BoardRepository {
    fun getUnlikedBoards(): Observable<ArrayList<Board>>
    fun getLikedBoards(): Observable<ArrayList<Board>>
}