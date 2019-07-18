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
import kotlin.reflect.KClass

class CommunityViewModel(
    private val repository: BoardRepository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val _isProgress = MutableLiveData<Int>()
    val isProgress: LiveData<Int> get() = _isProgress

    private val _likedBoards = MutableLiveData<ArrayList<Board>>()
    val likedBoards: LiveData<ArrayList<Board>> get() = _likedBoards

    private val _unlikedBoards = MutableLiveData<ArrayList<Board>>()
    val unlikedBoards: LiveData<ArrayList<Board>> get() = _unlikedBoards

    private val _activityToStart = MutableLiveData<Pair<KClass<*>, Bundle?>>()
    val activityToStart: LiveData<Pair<KClass<*>, Bundle?>> get() = _activityToStart

    init {
        getLikedBoards()
        getUnlikedBoards()
    }

    private fun getLikedBoards() {
        addDisposable(
            repository.getLikedBoards()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .doOnSubscribe { showProgress() }
                .doOnTerminate { hideProgress() }
                .subscribe({
                    it.run {
                        _likedBoards.postValue(this)
                    }
                }, {

                })
        )
    }

    private fun getUnlikedBoards() {
        addDisposable(
            repository.getUnlikedBoards()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .doOnSubscribe { showProgress() }
                .doOnTerminate { hideProgress() }
                .subscribe({
                    it.run {
                        _unlikedBoards.postValue(this)
                    }
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