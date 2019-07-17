package com.crecrew.crecre.ui.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.crecrew.crecre.base.BaseViewModel
import com.crecrew.crecre.ui.main.community.CommunityViewModel
import com.crecrew.crecre.util.scheduler.SchedulerProvider
import kotlin.reflect.KClass

class MainViewModel(
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val _activityToStart = MutableLiveData<Pair<KClass<*>, Bundle?>>()
    val activityToStart: LiveData<Pair<KClass<*>, Bundle?>> get() = _activityToStart

    fun navigate(activity: KClass<*>) {
        val bundle = Bundle()
        _activityToStart.postValue(Pair(activity, bundle))
    }

    companion object {
        private val TAG = "MainViewModel"
    }
}