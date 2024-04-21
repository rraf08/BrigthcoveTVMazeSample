package com.sample.brigthcovetvmazesample.feature.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sample.brigthcovetvmazesample.feature.data.base.Failure
import com.sample.brigthcovetvmazesample.feature.data.remote.api.ApiService
import com.sample.brigthcovetvmazesample.feature.domain.model.schedule.Schedule
import io.reactivex.annotations.NonNull
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

private const val STARTING_PAGE_INDEX = 1

class ScheduleRemotePagingSource(private val apiService: ApiService) :
    PagingSource<Int, Schedule>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Schedule> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = apiService.getSchedule(page = position).map { it.toSchedule() }

            toLoadResult(response, position)
        } catch (e: Exception) {
            when (e) {
                is UnknownHostException, is SocketTimeoutException -> {
                    LoadResult.Error(
                        Failure.NoInternet(e.message)
                    )
                }

                is TimeoutException -> {
                    LoadResult.Error(
                        Failure.Timeout(e.message)
                    )
                }

                else -> {
                    LoadResult.Error(
                        Failure.Unknown(e.message)
                    )
                }
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Schedule>): Int? = state.anchorPosition

    private fun toLoadResult(
        @NonNull response: List<Schedule>,
        position: Int
    ): LoadResult<Int, Schedule> {

        return LoadResult.Page(
            data = response,
            prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
            nextKey = if (response.isEmpty()) null else position + 1,
            itemsBefore = LoadResult.Page.COUNT_UNDEFINED,
            itemsAfter = LoadResult.Page.COUNT_UNDEFINED
        )
    }


}