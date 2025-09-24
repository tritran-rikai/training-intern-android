package com.app.imagerandom.data.di

import android.content.Context
import com.app.imagerandom.data.repository.AppThemeRepository
import com.app.imagerandom.data.repository.AppThemeRepositoryImpl
import com.app.imagerandom.data.repository.ImageRandomRepository
import com.app.imagerandom.data.repository.ImageRandomRepositoryImpl
import com.app.imagerandom.domain.usecase.GetListImageRandomUseCase
import com.app.imagerandom.domain.usecase.theme.GetThemeUseCase
import com.app.imagerandom.domain.usecase.theme.SetThemeUseCase
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.greenrobot.eventbus.EventBus
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val client = OkHttpClient.Builder()
//            .addInterceptor(interceptor)
//            .build()
//
//        return Retrofit.Builder()
//            .baseUrl(AppConfig.FILE_HOST)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//    }

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideEventBus(): EventBus {
        return EventBus.getDefault()
    }

    @Provides
    @Singleton
    fun fusedLocationClient(context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }


    @Provides
    fun provideAppThemeRepository(
        @ApplicationContext context: Context
    ): AppThemeRepository {
        return AppThemeRepositoryImpl(context)
    }

    @Provides
    fun provideGetListImageRandomUseCase(imageRandomRepository: ImageRandomRepository): GetListImageRandomUseCase {
        return GetListImageRandomUseCase(imageRandomRepository)
    }

    @Provides
    fun provideImageRandomRepository(): ImageRandomRepository {
        return ImageRandomRepositoryImpl()
    }

    @Provides
    fun provideGetThemeUseCase(appThemeRepository: AppThemeRepository): GetThemeUseCase {
        return GetThemeUseCase(appThemeRepository)
    }

    @Provides
    fun provideSetThemeUseCase(appThemeRepository: AppThemeRepository): SetThemeUseCase {
        return SetThemeUseCase(appThemeRepository)
    }
}