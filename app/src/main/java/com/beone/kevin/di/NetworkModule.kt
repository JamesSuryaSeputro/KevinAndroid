package com.beone.kevin.di

import com.beone.kevin.remote.RetrofitService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val networkModule =  module {

    fun provideGson() = GsonConverterFactory.create()

    fun provideRxJavaCallAdapter() = RxJava2CallAdapterFactory.create()

    fun provideRetrofit(
        converterAdapter: GsonConverterFactory
    ) =
        Retrofit.Builder()
            .baseUrl("http://192.168.0.108:8080/apitki/public/")
            .addConverterFactory(converterAdapter)
            .build()

    fun provideApiDataService(retrofit: Retrofit) = retrofit.create(RetrofitService::class.java)

    single<GsonConverterFactory> {
        provideGson()
    }
    single<RxJava2CallAdapterFactory> {
        provideRxJavaCallAdapter()
    }
    single<Retrofit> {
        provideRetrofit(get())
    }
    single<RetrofitService> {
        provideApiDataService(get())
    }

}
