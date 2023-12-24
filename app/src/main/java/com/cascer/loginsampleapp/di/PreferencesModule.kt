package com.cascer.loginsampleapp.di

import android.content.Context
import com.cascer.loginsampleapp.utils.AppPreferences
import com.cascer.loginsampleapp.utils.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {
    @Singleton
    @Provides
    fun provideAppPreferences(@ApplicationContext context: Context): AppPreferences {
        return AppPreferences.getInstance(context.dataStore)
    }
}