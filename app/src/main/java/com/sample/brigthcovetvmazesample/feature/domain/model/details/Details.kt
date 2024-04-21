package com.sample.brigthcovetvmazesample.feature.domain.model.details

import com.sample.brigthcovetvmazesample.feature.domain.model.schedule.Image

data class Details(
    val id: Long,
    val url: String,
    val name: String,
    val genres: List<String>,
    val premiered: String,
    val officialSite: String?,
    val image: Image,
    val summary: String,
    val updated: Long,
    val actors: List<Person>
)
