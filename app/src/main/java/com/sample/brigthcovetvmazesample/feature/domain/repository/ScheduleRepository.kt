package com.sample.brigthcovetvmazesample.feature.domain.repository

import androidx.paging.PagingData
import com.sample.brigthcovetvmazesample.feature.domain.common.ResultState
import com.sample.brigthcovetvmazesample.feature.domain.model.details.Details
import com.sample.brigthcovetvmazesample.feature.domain.model.schedule.Schedule
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {

    fun getSchedule(pageSize: Int): Flow<PagingData<Schedule>>

    suspend fun getDetails(id: Long): ResultState<Details?>
}