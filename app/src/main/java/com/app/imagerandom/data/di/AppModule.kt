package com.app.imagerandom.data.di

import android.content.Context
import com.app.imagerandom.data.local.SharedPrefHelper
import com.app.imagerandom.data.network.MovieApiService
import com.app.imagerandom.data.repository.AuthRepository
import com.app.imagerandom.data.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideSharedPrefHelper(
        @ApplicationContext context: Context
    ): SharedPrefHelper {
        return SharedPrefHelper(context)
    }
}