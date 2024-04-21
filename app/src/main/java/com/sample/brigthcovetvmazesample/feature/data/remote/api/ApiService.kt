package com.sample.brigthcovetvmazesample.feature.data.remote.api

import com.sample.brigthcovetvmazesample.feature.data.remote.dto.details.DetailsDto
import com.sample.brigthcovetvmazesample.feature.data.remote.dto.schedule.ScheduleDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    /**
     *  Get The list of Tvh shows with pagination
     *
     *  @param page [Int] page number to be fetched
     *  @return [List]<[ScheduleDto]>
     */
    @GET("shows")
    suspend fun getSchedule(
        @Query("page") page: Int
    ): List<ScheduleDto>

    /**
     *  Get the detail about a tv show with cast
     *
     *  @param id [Int] the id of the tv show
     *  @return [DetailsDto]
     */
    @GET("shows/{id}?embed=cast")
    suspend fun getDetails(
        @Path("id") id: Long
    ): DetailsDto?

}