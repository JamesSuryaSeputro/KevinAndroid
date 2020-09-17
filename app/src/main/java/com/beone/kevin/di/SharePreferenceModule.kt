package com.beone.kevin.di

import android.app.Application
import android.content.Context
import com.beone.kevin.SharedPreferenceUtils
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val SharePreferenceModule = module {
    single(named("UserPrefs")) {
        provideSharePreference(androidApplication())
    }
    factory {
        SharedPreferenceUtils(get(named("UserPrefs")))
    }
}

fun provideSharePreference(application:Application) = application.getSharedPreferences("config",
    Context.MODE_PRIVATE)

