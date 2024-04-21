package com.sample.brigthcovetvmazesample.feature.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.brigthcovetvmazesample.feature.domain.common.ResultState
import com.sample.brigthcovetvmazesample.feature.domain.model.details.Details
import com.sample.brigthcovetvmazesample.feature.domain.usecase.ScheduleUseCases
import com.sample.brigthcovetvmazesample.util.SCHEDULE_ID_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val scheduleUseCases: ScheduleUseCases,
    saveStateHandle: SavedStateHandle
) : ViewModel() {

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    private val _showDetailsState: MutableStateFlow<Details?> = MutableStateFlow(value = null)
    val detailsState: StateFlow<Details?>
        get() = _showDetailsState


    init {

        val id: Long = saveStateHandle[SCHEDULE_ID_KEY]!!
        try {
            viewModelScope.launch {
                _isLoading.update { value -> true }
                when (val response = scheduleUseCases.getDetails(id = id)) {
                    is ResultState.Success -> (response.data)
                    else -> null
                }
                _isLoading.update { value -> false }
            }
        } catch (e: Exception) {
        }


    }
}