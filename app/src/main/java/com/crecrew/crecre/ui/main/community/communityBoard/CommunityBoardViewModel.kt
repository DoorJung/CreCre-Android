package com.crecrew.crecre.ui.main.community.communityBoard

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.crecrew.crecre.base.BaseViewModel
import com.crecrew.crecre.data.model.posts.PostRepository
import com.crecrew.crecre.data.model.posts.Posts
import com.crecrew.crecre.util.scheduler.SchedulerProvider
import io.reactivex.Single
import kotlin.reflect.KClass


class CommunityBoardViewModel(
    private val repository: PostRepository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val _isProgress = MutableLiveData<Int>()
    val isProgress: LiveData<Int> get() = _isProgress

    private val _posts = MutableLiveData<ArrayList<Posts>>()
    val posts: LiveData<ArrayList<Posts>> get() = _posts

    private val _activityToStart = MutableLiveData<Pair<KClass<*>, Bundle?>>()
    val activityToStart: LiveData<Pair<KClass<*>, Bundle?>> get() = _activityToStart

    fun getPosts(boardIdx: Int) {
        lateinit var repositoryGetPosts: Single<ArrayList<Posts>>

        if (boardIdx == 0) repositoryGetPosts = repository.getNewPosts()
        else repositoryGetPosts = repository.getHotPosts()

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
        private val TAG = "CommunityBoardViewModel"
    }
}