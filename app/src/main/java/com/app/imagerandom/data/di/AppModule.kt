package com.app.imagerandom.data.di

import com.app.imagerandom.data.network.MovieApiService
import com.app.imagerandom.data.repository.AuthRepository
import com.app.imagerandom.data.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAuthRepository(api: MovieApiService): AuthRepository {
        return AuthRepositoryImpl(api)
    }
}