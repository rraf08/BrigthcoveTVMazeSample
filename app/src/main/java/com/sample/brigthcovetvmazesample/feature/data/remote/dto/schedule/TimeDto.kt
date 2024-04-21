package com.sample.brigthcovetvmazesample.feature.data.remote.dto.schedule

import android.os.Parcelable
import com.sample.brigthcovetvmazesample.feature.domain.model.schedule.Time
import kotlinx.parcelize.Parcelize

@Parcelize
data class TimeDto(
    val time: String,
    val days: List<String>
) : Parcelable {

    fun toTime(): Time {
        return Time(
            time,
            days
        )
    }

}
