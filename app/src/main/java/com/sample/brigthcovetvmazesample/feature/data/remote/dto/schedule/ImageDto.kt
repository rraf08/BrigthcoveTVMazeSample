package com.sample.brigthcovetvmazesample.feature.data.remote.dto.schedule

import android.os.Parcelable
import com.sample.brigthcovetvmazesample.feature.domain.model.schedule.Image
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageDto(
    val medium: String,
    val original: String
) : Parcelable {

    fun toImage(): Image {
        return Image(
            medium,
            original
        )
    }

}
