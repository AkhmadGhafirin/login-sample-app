package com.cascer.loginsampleapp.di

import com.cascer.loginsampleapp.domain.usecase.UserUseCase
import com.cascer.loginsampleapp.domain.usecase.UserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideUserUseCase(userUseCase: UserUseCaseImpl): UserUseCase
}