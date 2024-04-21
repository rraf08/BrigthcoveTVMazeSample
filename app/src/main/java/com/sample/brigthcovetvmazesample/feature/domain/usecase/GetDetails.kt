package com.sample.brigthcovetvmazesample.feature.domain.usecase

import com.sample.brigthcovetvmazesample.feature.domain.common.ResultState
import com.sample.brigthcovetvmazesample.feature.domain.model.details.Details
import com.sample.brigthcovetvmazesample.feature.domain.repository.ScheduleRepository

class GetDetails(private val repository: ScheduleRepository) {

    operator suspend fun invoke(id: Long): ResultState<Details?> {
        return repository.getDetails(id = id)
    }
}