package com.sample.brigthcovetvmazesample.feature.data.remote.dto.details

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sample.brigthcovetvmazesample.feature.data.remote.dto.schedule.ImageDto
import com.sample.brigthcovetvmazesample.feature.domain.model.details.Details
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsDto(
    val id: Long,
    val url: String,
    val name: String,
    val genres: List<String>,
    val premiered: String,
    val officialSite: String?,
    val image: ImageDto,
    val summary: String,
    val updated: Long,
    @SerializedName("_embedded") val embedded: EmbeddedDto,
) : Parcelable {

    fun toDetails(): Details {
        return Details(
            id = id,
            url = url,
            name = name,
            genres = genres,
            premiered = premiered,
            officialSite = officialSite,
            image = image.toImage(),
            summary = summary,
            updated = updated,
            actors = embedded.cast.map { it.person.toPerson() }
        )
    }

}
