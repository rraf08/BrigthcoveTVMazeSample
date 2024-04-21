package com.sample.brigthcovetvmazesample.feature.presentation.schedule

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sample.brigthcovetvmazesample.feature.domain.model.schedule.Schedule

@Composable
fun ScheduleItem(
    scheduleItem: Schedule,
    modifier: Modifier,
    onClick: (Long) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(scheduleItem.id) },
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {

        Card(
            modifier = modifier
                .fillMaxHeight(),
            shape = MaterialTheme.shapes.large,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            )

        ) {

            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .clickable { onClick(scheduleItem.id) },
                horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
            ) {

                AsyncImage(
                    model = scheduleItem.image.medium,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .requiredWidthIn(100.dp, 200.dp)
                        .requiredHeightIn(200.dp, 400.dp)
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = scheduleItem.name,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    Row {
                        scheduleItem.genres.forEach { genre ->

                            Text(
                                text = genre,
                                modifier = Modifier.padding(top = 6.dp, bottom = 6.dp, end = 2.dp),
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    Text(
                        text = scheduleItem.time.time,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp, end = 2.dp)
                    )

                    Row {
                        scheduleItem.time.days.forEach { day ->
                            Text(
                                text = day,
                                modifier = Modifier.padding(top = 6.dp, bottom = 6.dp, end = 8.dp),
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 10.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }


                }
            }

        }


    }

}
