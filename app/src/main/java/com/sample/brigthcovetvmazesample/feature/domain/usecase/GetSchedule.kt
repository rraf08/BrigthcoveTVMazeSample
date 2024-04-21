package com.sample.brigthcovetvmazesample.feature.domain.usecase

import androidx.paging.PagingData
import com.sample.brigthcovetvmazesample.feature.domain.model.schedule.Schedule
import com.sample.brigthcovetvmazesample.feature.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow

class GetSchedule(private val repository: ScheduleRepository) {

    operator fun invoke(pageSize: Int): Flow<PagingData<Schedule>> {
        return repository.getSchedule(pageSize = pageSize)
    }
}