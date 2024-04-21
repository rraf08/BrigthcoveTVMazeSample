package com.sample.brigthcovetvmazesample.feature.presentation.schedule

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.sample.brigthcovetvmazesample.ui.component.PaginationStateHandler
import com.sample.brigthcovetvmazesample.ui.component.WarningMessage

@Composable
fun ScheduleScreen(
    viewModel: ScheduleViewModel,
    onScheduleClick: (Long) -> Unit
) {

    val scheduleState = viewModel.scheduleState.collectAsLazyPagingItems()

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 30.dp),
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxSize()

    ) {


        items(
            items = scheduleState,
            key = { it.id })
        {
            it?.let { scheduleItem ->
                ScheduleItem(
                    scheduleItem = scheduleItem,
                    modifier = Modifier.requiredHeight(height = 180.dp),
                    onClick = {
                        onScheduleClick
                    }
                )
            }

        }

        item {
            PaginationStateHandler(
                paginationState = scheduleState,
                loadingComponent = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                },
                errorComponent = {
                    WarningMessage(

                        text = "Loading error",
                        trailingContent = {
                            Text(
                                text = "Retry",
                                modifier = Modifier
                                    .padding(start = 0.dp)
                                    .clickable(role = Role.Button) {
                                        scheduleState.retry()
                                    },
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.primary
                            )
                        }
                    )
                }
            )

        }

    }

}