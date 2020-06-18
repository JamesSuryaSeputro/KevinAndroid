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
        callAdapter: RxJava2CallAdapterFactory,
        converterAdapter: GsonConverterFactory,
        client: OkHttpClient
    ) =
        Retrofit.Builder()
            .client(client)
            .baseUrl("")
            .addCallAdapterFactory(callAdapter)
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
        provideRetrofit(get(), get(), get())
    }
    single<RetrofitService> {
        provideApiDataService(get())
    }

}
