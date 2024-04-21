package com.sample.brigthcovetvmazesample.feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sample.brigthcovetvmazesample.feature.data.base.BaseRemoteRepository
import com.sample.brigthcovetvmazesample.feature.data.remote.api.ApiService
import com.sample.brigthcovetvmazesample.feature.data.repository.paging.ScheduleRemotePagingSource
import com.sample.brigthcovetvmazesample.feature.domain.common.ResultState
import com.sample.brigthcovetvmazesample.feature.domain.model.details.Details
import com.sample.brigthcovetvmazesample.feature.domain.model.schedule.Schedule
import com.sample.brigthcovetvmazesample.feature.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow

class ScheduleRepositoryImpl(private val apiService: ApiService) : ScheduleRepository,
    BaseRemoteRepository() {

    override fun getSchedule(pageSize: Int): Flow<PagingData<Schedule>> {
        return Pager(
            config = PagingConfig(pageSize = pageSize),
            pagingSourceFactory = { ScheduleRemotePagingSource(apiService = apiService) }
        ).flow
    }

    override suspend fun getDetails(id: Long): ResultState<Details?> {
        return when (val response = safeApiCall { apiService.getDetails(id = id) }) {
            is ResultState.Loading -> ResultState.Loading()
            is ResultState.Success -> ResultState.Success(data = response.data?.toDetails())
            is ResultState.Error -> ResultState.Error(throwable = response.throwable)
        }
    }

}