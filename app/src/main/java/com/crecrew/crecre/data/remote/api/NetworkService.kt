package com.crecrew.crecre.data.remote.api

import com.crecrew.crecre.CreCreApplication.Companion.globalApplication
import com.crecrew.crecre.R
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface NetworkService {

    companion object {
        fun create(): NetworkService {
            return Retrofit.Builder()
                .baseUrl(globalApplication.getString(R.string.base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkService::class.java)
        }
    }
}