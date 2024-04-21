package com.sample.brigthcovetvmazesample.feature.presentation.common

sealed class Screen(val route: String) {
    object ScheduleScreen : Screen(route = "schedule")
    object DetailScreen : Screen(route = "details/{scheduleId}")
}