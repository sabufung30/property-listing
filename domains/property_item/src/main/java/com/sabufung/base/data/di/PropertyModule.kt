package com.sabufung.base.data.di

import com.sabufung.base.data.HomegatePropertyRepository
import com.sabufung.base.data.repositories.PropertyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class PropertyModule {

    @Binds
    abstract fun bindListingRepository(impl: HomegatePropertyRepository): PropertyRepository

}