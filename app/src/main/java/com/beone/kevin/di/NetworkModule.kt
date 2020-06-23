package com.beone.kevin.di

import com.beone.kevin.remote.RetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val HostLocal  = "http://192.168.0.106:8080/apitki/public/"
const val HostGCP = "https://krisjaya-2020.et.r.appspot.com/"
val networkModule = module {

    fun provideGson() = GsonConverterFactory.create()
    fun provideHttpLoging() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    fun provideClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

    fun provideRxJavaCallAdapter() = RxJava2CallAdapterFactory.create()

    fun provideRetrofit(
        converterAdapter: GsonConverterFactory
    ,okHttpClient: OkHttpClient
    ) =
        Retrofit.Builder()
            .baseUrl(HostLocal)
            .client(okHttpClient)
            .addConverterFactory(converterAdapter)
            .build()

    fun provideApiDataService(retrofit: Retrofit) = retrofit.create(RetrofitService::class.java)

    single<GsonConverterFactory> {
        provideGson()
    }
    single<OkHttpClient> {
        provideClient(get())
    }
    single<HttpLoggingInterceptor> {
        provideHttpLoging()
    }
    single<RxJava2CallAdapterFactory> {
        provideRxJavaCallAdapter()
    }
    single<Retrofit> {
        provideRetrofit(get(),get())
    }

    single<RetrofitService> {
        provideApiDataService(get())
    }

}
