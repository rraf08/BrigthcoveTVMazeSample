package com.sample.brigthcovetvmazesample.feature.data.remote.dto.details

import android.os.Parcelable
import com.sample.brigthcovetvmazesample.feature.data.remote.dto.schedule.ImageDto
import com.sample.brigthcovetvmazesample.feature.domain.model.details.Person
import kotlinx.parcelize.Parcelize


@Parcelize
data class PersonDto(
    val id: Long,
    val image: ImageDto?,
    val name: String,
    val url: String,
    val updated: Long
) : Parcelable {

    fun toPerson(): Person {
        return Person(
            id,
            image?.toImage(),
            name,
            updated,
            url
        )
    }

}