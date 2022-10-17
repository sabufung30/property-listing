package com.sabufung.data.api.retrofit

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Reusable
    @Provides
    fun provideRetrofit(
        baseUrl: String,
        moshi: Moshi
    ): Retrofit {
        val moshiConverterFactory = MoshiConverterFactory.create(moshi)
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Reusable
    @Provides
    fun provideBaseUrl() = "https://private-9f1bb1-homegate3.apiary-mock.com/"

    @Reusable
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

}
