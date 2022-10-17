package com.sabufung.navigation

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class NavigationModule {

    @ActivityRetainedScoped
    @Provides
    fun provideNavigationDispatcher(): NavDispatcher = NavDispatcher()
}
