package com.example.myapplication.di

import com.example.myapplication.repository.SharedRepository
import com.example.myapplication.repository.SharedRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideSharedRepository(repo: SharedRepositoryImpl): SharedRepository
}