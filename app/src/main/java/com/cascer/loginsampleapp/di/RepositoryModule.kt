package com.cascer.loginsampleapp.di

import com.cascer.loginsampleapp.data.UserRepositoryImpl
import com.cascer.loginsampleapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [PreferencesModule::class, NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideUserRepository(userRepository: UserRepositoryImpl): UserRepository
}