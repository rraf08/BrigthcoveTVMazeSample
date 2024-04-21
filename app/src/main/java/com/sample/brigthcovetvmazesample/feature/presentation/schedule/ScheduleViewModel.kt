package com.sample.brigthcovetvmazesample.feature.presentation.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sample.brigthcovetvmazesample.feature.domain.model.schedule.Schedule
import com.sample.brigthcovetvmazesample.feature.domain.usecase.ScheduleUseCases
import com.sample.brigthcovetvmazesample.util.PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleUseCases: ScheduleUseCases
) : ViewModel() {

    private val _scheduleState: MutableStateFlow<PagingData<Schedule>> =
        MutableStateFlow(value = PagingData.empty())
    val scheduleState: StateFlow<PagingData<Schedule>>
        get() = _scheduleState

    init {
        viewModelScope.launch {
            scheduleUseCases.getSchedule(pageSize = PAGE_SIZE)
                .cachedIn(viewModelScope)
                .distinctUntilChanged()
                .collect {
                    _scheduleState.value = it
                }
        }

    }

}