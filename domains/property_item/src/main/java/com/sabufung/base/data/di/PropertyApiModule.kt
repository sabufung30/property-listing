package com.sabufung.base.data.di

import com.sabufung.base.data.api.PropertyService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
object PropertyApiModule {

    @Provides
    @Reusable
    fun providePropertyService(retrofit: Retrofit): PropertyService = retrofit.create(
        PropertyService::class.java)

}