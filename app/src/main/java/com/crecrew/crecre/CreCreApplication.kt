package com.crecrew.crecre

import android.app.Application
import com.crecrew.crecre.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CreCreApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        globalApplication = this
        startKoin {
            androidLogger()
            androidContext(this@CreCreApplication)
            modules(appModule)
        }
    }

    companion object {
        lateinit var globalApplication: CreCreApplication
    }
}