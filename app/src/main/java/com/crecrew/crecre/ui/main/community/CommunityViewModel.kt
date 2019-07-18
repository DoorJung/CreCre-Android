package com.crecrew.crecre.ui.main.community

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.os.Bundle
import android.util.Log
import com.crecrew.crecre.base.BaseViewModel
import com.crecrew.crecre.data.model.board.Board
import com.crecrew.crecre.data.model.board.BoardRepository
import com.crecrew.crecre.util.scheduler.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.Single
import kotlin.reflect.KClass

class CommunityViewModel(
    private val repository: BoardRepository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val _isProgress = MutableLiveData<Int>()
    val isProgress: LiveData<Int> get() = _isProgress

    private val _boards = MutableLiveData<ArrayList<Board>>()
    val boards: LiveData<ArrayList<Board>> get() = _boards

    private val _activityToStart = MutableLiveData<Pair<KClass<*>, Bundle?>>()
    val activityToStart: LiveData<Pair<KClass<*>, Bundle?>> get() = _activityToStart

    init {
        getBoards()
    }

    fun getBoards() {
        addDisposable(
            repository.getBoards()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .doOnSubscribe { showProgress() }
                .doOnTerminate { hideProgress() }
                .subscribe({
                    it.run {
                        _boards.postValue(this)
                    }
                }, {

                })
        )
    }

    fun likeBoard(isLove: Int, boardIdx: Int) {
        lateinit var repositoryLike: Single<Boolean>
        if (isLove == 0)
            repositoryLike = repository.likeBoard(boardIdx)
        else
            repositoryLike = repository.cancelLikeBoard(boardIdx)
        addDisposable(
            repositoryLike
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .doOnSubscribe { showProgress() }
                .doOnTerminate { hideProgress() }
                .subscribe({
                    if(it)
                        getBoards()
                }, {

                })
        )
    }

    fun navigate(activity: KClass<*>, boardIdx: Int, title: String) {
        val bundle = Bundle().apply { putInt("boardIdx", boardIdx); putString("title", title) }
        _activityToStart.postValue(Pair(activity, bundle))
    }

    private fun showProgress() {
        _isProgress.value = View.VISIBLE
    }

    private fun hideProgress() {
        _isProgress.value = View.INVISIBLE
    }

    companion object {
        private val TAG = "CommunityViewModel"
    }
}