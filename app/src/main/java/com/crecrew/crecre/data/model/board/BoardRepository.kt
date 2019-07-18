package com.crecrew.crecre.data.model.board

import io.reactivex.Observable
import io.reactivex.Single

interface BoardRepository {
    fun getLikedBoards(): Observable<ArrayList<Board>>
    fun getUnlikedBoards(): Observable<ArrayList<Board>>
    fun getBoards(): Observable<ArrayList<Board>>
    fun likeBoard(boardIdx: Int): Single<Boolean>
    fun cancelLikeBoard(boardIdx: Int): Single<Boolean>
}