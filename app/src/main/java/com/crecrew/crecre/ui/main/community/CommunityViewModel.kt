package com.crecrew.crecre.ui.main.community

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.os.Bundle
import android.util.Log
import com.crecrew.crecre.base.BaseViewModel
import com.crecrew.crecre.util.scheduler.SchedulerProvider
import kotlin.reflect.KClass


class CommunityViewModel(
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val _isProgress = MutableLiveData<Int>()
    val isProgress: LiveData<Int> get() = _isProgress

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