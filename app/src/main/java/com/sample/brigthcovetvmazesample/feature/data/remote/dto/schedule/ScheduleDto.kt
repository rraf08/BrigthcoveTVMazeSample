package com.sample.brigthcovetvmazesample.feature.data.remote.dto.schedule

import android.os.Parcelable
import com.sample.brigthcovetvmazesample.feature.domain.model.schedule.Schedule
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScheduleDto(
    val id: Long,
    val name: String,
    val genres: List<String>,
    val image: ImageDto,
    val updated: Long,
    val schedule: TimeDto
) : Parcelable {

    fun toSchedule(): Schedule {
        return Schedule(
            id = id,
            name = name,
            genres = genres,
            image = image.toImage(),
            time = schedule.toTime(),
            updated = updated
        )
    }
}
