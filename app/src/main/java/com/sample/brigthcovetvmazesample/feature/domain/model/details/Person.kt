package com.sample.brigthcovetvmazesample.feature.domain.model.details

import com.sample.brigthcovetvmazesample.feature.domain.model.schedule.Image

data class Person(
    val id: Long,
    val image: Image?,
    val name: String,
    val updated: Long,
    val url: String
)
