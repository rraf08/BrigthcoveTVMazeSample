package com.sample.brigthcovetvmazesample.feature.data.remote.dto.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmbeddedDto(
    val cast: List<CastDto>
) : Parcelable
