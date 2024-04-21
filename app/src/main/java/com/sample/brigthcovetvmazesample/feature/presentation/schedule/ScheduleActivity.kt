package com.sample.brigthcovetvmazesample.feature.presentation.schedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sample.brigthcovetvmazesample.feature.presentation.common.Screen
import com.sample.brigthcovetvmazesample.feature.presentation.details.DetailScreen
import com.sample.brigthcovetvmazesample.feature.presentation.details.DetailsViewModel
import com.sample.brigthcovetvmazesample.ui.theme.BrigthcoveTVMazeSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {

            val navController = rememberNavController()
            val uriHandler = LocalUriHandler.current

            BrigthcoveTVMazeSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ScheduleScreen.route
                    ) {
                        composable(route = Screen.ScheduleScreen.route) {
                            val scheduleViewModel = hiltViewModel<ScheduleViewModel>()
                            ScheduleScreen(
                                viewModel = scheduleViewModel,
                                onScheduleClick = { id ->
                                    navController.navigate(route = "details/$id")
                                })
                        }
                        composable(route = Screen.DetailScreen.route) {
                            val detailViewModel = hiltViewModel<DetailsViewModel>()
                            DetailScreen(
                                viewModel = detailViewModel,
                                onBackPress = { navController.popBackStack() },
                                RedirectClick = { uriHandler.openUri(uri = it) }
                            )
                        }

                    }
                }

            }
        }
    }
}
