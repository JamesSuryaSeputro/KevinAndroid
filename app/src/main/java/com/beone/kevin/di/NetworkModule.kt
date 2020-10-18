package com.beone.kevin.di

import com.beone.kevin.BuildConfig
import com.beone.kevin.remote.RetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



const val HostLocal = "http://192.168.1.9/apitki/public/"
const val HostGCP = "https://krisjaya-2020.et.r.appspot.com/"

fun provideIP(): String {
    if (BuildConfig.DEBUG) {
        return HostGCP
    } else {
        return HostLocal
    }
}


val networkModule = module {

    fun provideGson() = GsonConverterFactory.create()

    fun provideHttpLoging() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    fun provideClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()

    fun provideRetrofit(
        converterAdapter: GsonConverterFactory
        , okHttpClient: OkHttpClient,
        ipaddress: String
    ) =
        Retrofit.Builder()
            .baseUrl(ipaddress)
            .client(okHttpClient)
            .addConverterFactory(converterAdapter)
            .build()

    fun provideApiDataService(retrofit: Retrofit) = retrofit.create(RetrofitService::class.java)

    single<String>(named("host")) {
        provideIP()
    }
    single<GsonConverterFactory> {
        provideGson()
    }

    single<OkHttpClient> {
        provideClient(get())
    }

    single<HttpLoggingInterceptor> {
        provideHttpLoging()
    }

    single<Retrofit> {
        provideRetrofit(get(), get(), get(named("host")))
    }

    single<RetrofitService> {
        provideApiDataService(get())
    }

}
