package com.sample.brigthcovetvmazesample.feature.domain.model.schedule

data class Schedule(
    val id: Long,
    val name: String,
    val genres: List<String>,
    val image: Image,
    val time: Time,
    val updated: Long
)