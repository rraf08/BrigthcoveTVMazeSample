package com.sample.brigthcovetvmazesample.feature.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sample.brigthcovetvmazesample.ui.component.BackButton
import com.sample.brigthcovetvmazesample.ui.component.TopBar
import com.sample.brigthcovetvmazesample.ui.component.WarningMessage
import com.sample.brigthcovetvmazesample.util.DEFAULT_IMAGE
import com.sample.brigthcovetvmazesample.util.removeHtmlTags


@Composable
fun DetailScreen(
    viewModel: DetailsViewModel,
    onBackPress: () -> Unit,
    RedirectClick: (String) -> Unit
) {
    val isLoading by viewModel.isLoading
        .collectAsState()

    val details by viewModel.detailsState
        .collectAsState()

    val screenHeight = LocalContext.current.resources.displayMetrics.heightPixels.dp /
            LocalDensity.current.density

    Column(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            TopBar(
                modifier = Modifier.padding(all = 18.dp),
                leadingIcon = {
                    BackButton(onBackPress = { onBackPress() })
                }
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            details?.let { detailsItem ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = screenHeight * 0.8f)
                )
                {
                    AsyncImage(
                        model = detailsItem.image.original,
                        contentDescription = "This is an example image",
                    )
                }
                TopBar(
                    modifier = Modifier.padding(all = 18.dp),
                    leadingIcon = {
                        BackButton(onBackPress = { onBackPress() })
                    }
                )
                Text(
                    text = detailsItem.name,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(top = 10.dp, bottom = 5.dp),
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = detailsItem.premiered.split("-").first(),
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.secondary
                )

                Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
                    detailsItem.genres.forEach { genre ->
                        Text(
                            text = genre,
                            modifier = Modifier.padding(horizontal = 5.dp),
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text(
                    text = "Summary",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(all = 10.dp),
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.primary
                )

                Text(
                    text = detailsItem.summary.removeHtmlTags(),
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text(
                    text = "Cast and crew",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(all = 10.dp),
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.primary
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(space = 10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    items(items = detailsItem.actors) { actor ->
                        Column(
                            modifier = Modifier
                                .requiredHeight(height = 200.dp)
                                .requiredWidth(width = 160.dp)
                        )
                        {
                            AsyncImage(
                                model = actor.image?.medium ?: DEFAULT_IMAGE,
                                contentDescription = "This is an example image",
                            )
                            Text(
                                text = actor.name,
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterHorizontally),
                                style = MaterialTheme.typography.caption,
                                color = MaterialTheme.colors.primary
                            )
                        }

                    }

                }

                Button(
                    onClick = { RedirectClick(detailsItem.url) },
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Watch Now"
                    )
                }

            } ?: WarningMessage(text = "Not found")

        }
    }

}