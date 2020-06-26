package com.beone.kevin

import android.app.Application
import com.beone.kevin.di.SharePrefrenceModule
import com.beone.kevin.di.networkModule
import com.beone.kevin.di.viewModelModule
import com.bumptech.glide.annotation.GlideModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApp)
            modules(listOf(
                networkModule,
                viewModelModule,
                SharePrefrenceModule
            ))
        }
    }
}