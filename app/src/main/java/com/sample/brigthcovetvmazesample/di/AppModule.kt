package com.sample.brigthcovetvmazesample.di

import android.content.Context
import com.sample.brigthcovetvmazesample.application.BrightcoveTVMazeApplication
import com.sample.brigthcovetvmazesample.feature.data.remote.api.ApiService
import com.sample.brigthcovetvmazesample.feature.data.repository.ScheduleRepositoryImpl
import com.sample.brigthcovetvmazesample.feature.domain.repository.ScheduleRepository
import com.sample.brigthcovetvmazesample.feature.domain.usecase.GetDetails
import com.sample.brigthcovetvmazesample.feature.domain.usecase.GetSchedule
import com.sample.brigthcovetvmazesample.feature.domain.usecase.ScheduleUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesBrightcoveTVMazeApplication(
        @ApplicationContext app: Context
    ): BrightcoveTVMazeApplication {
        return app as BrightcoveTVMazeApplication
    }

    @Singleton
    @Provides
    fun providesApi(apiFactory: ApiFactory): ApiService {
        return apiFactory.createInstance(api = ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesTvShowRepository(apiService: ApiService): ScheduleRepository {
        return ScheduleRepositoryImpl(apiService = apiService)
    }

    @Singleton
    @Provides
    fun providesTvShowUseCases(repository: ScheduleRepository): ScheduleUseCases {
        return ScheduleUseCases(
            getSchedule = GetSchedule(repository = repository),
            getDetails = GetDetails(repository = repository)
        )
    }
}