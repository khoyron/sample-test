package com.sample.data.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import javax.inject.Singleton
import javax.net.ssl.SSLSession
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


@Module
class NetworkModule(private val mBaseUrl: String) {

    @Provides
    @Singleton
    internal fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    internal fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    //    @Singleton
    @Provides
    internal fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {

        return Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(gsonConverterFactory)
                .client(okhttp())
                .addCallAdapterFactory(provideRxJavaCallAdapterFactory())
                .build()

    }

    @Provides
    @Singleton
    internal fun okhttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val hostnameVerifier = object :HostnameVerifier{
            override fun verify(p0: String?, p1: SSLSession?): Boolean {
                return true
            }
        }

        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(7, TimeUnit.MINUTES)
                .hostnameVerifier(hostnameVerifier)
                .connectTimeout(7, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build()


        return okHttpClient
    }

}
