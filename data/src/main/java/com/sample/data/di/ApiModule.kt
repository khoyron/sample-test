package com.sample.data.di

import com.sample.data.network.UrlEndpoind
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    internal fun provideApiService(retrofit: Retrofit): UrlEndpoind {
        return retrofit.create(UrlEndpoind::class.java)
    }
}
