package com.crecrew.crecre.ui.main.community.board

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.crecrew.crecre.base.BaseViewModel
import com.crecrew.crecre.data.model.posts.PostRepository
import com.crecrew.crecre.data.model.posts.Posts
import com.crecrew.crecre.ui.main.community.CommunityViewModel
import com.crecrew.crecre.util.scheduler.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.Single
import kotlin.reflect.KClass

//0: All new, 1: All hot, else: posts by boardIdx
class BoardViewModel(
    private val repository: PostRepository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val _posts = MutableLiveData<ArrayList<Posts>>().apply { ArrayList<Posts>() }
    val posts: LiveData<ArrayList<Posts>> get() = _posts
    private val _isProgress = MutableLiveData<Int>()
    val isProgress: LiveData<Int> get() = _isProgress
    private val _activityToStart = MutableLiveData<Pair<KClass<*>, Bundle?>>()
    val activityToStart: LiveData<Pair<KClass<*>, Bundle?>> get() = _activityToStart

    fun getPosts(boardIdx: Int) {
        lateinit var repositoryGetPosts: Observable<ArrayList<Posts>>

        when (boardIdx) {
            0 -> repositoryGetPosts = repository.getAllNewPosts()
            1 -> repositoryGetPosts = repository.getAllHotPosts()
            else -> repositoryGetPosts = repository.getAllPostsByBoardIdx(boardIdx)
        }

        addDisposable(repositoryGetPosts
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.mainThread())
            .doOnSubscribe { showProgress() }
            .doOnTerminate { hideProgress() }
            .subscribe({
                it.run {
                    _posts.postValue(this)
                }
            }, {

            })
        )
    }

    fun navigate(activity: KClass<*>) {
        val bundle = Bundle()
        _activityToStart.postValue(Pair(activity, bundle))
    }

    private fun showProgress() {
        _isProgress.value = View.VISIBLE
    }

    private fun hideProgress() {
        _isProgress.value = View.INVISIBLE
    }

    companion object {
        private val TAG = "MainViewModel"
    }
}