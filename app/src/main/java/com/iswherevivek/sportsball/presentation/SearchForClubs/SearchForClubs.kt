package com.iswherevivek.sportsball.presentation.SearchForClubs

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.iswherevivek.sportsball.Dimens
import com.iswherevivek.sportsball.presentation.common.SearchBar

@Composable
fun SearchForClubs(
    state: SfcState,
    event: (SfcEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Dimens.MediumPadding1) // Adjust vertical padding as needed
            ) {
                Text(
                    text = "All clubs",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            item {
                SearchBar(
                    text = state.searchquery,
                    hint = "Search clubs",
                    readonly = false,
                    onValueChange = { event(SfcEvent.UpdateSearchQuery(it)) },
                    onSearch = {
                        event(SfcEvent.SearchClub)
                    }
                )
            }
            item {
                Column(modifier = Modifier.padding(horizontal = 12.dp)) {

                    state.club?.let {
                        AsyncImage(
                            modifier = Modifier
                                .size(Dimens.ArticleCardSize)
                                .clip(MaterialTheme.shapes.large),
                            model = ImageRequest.Builder(context).data(it.strTeamLogo).build(),
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                        )
                        PropertyItem(propertyName = "Team name", propertyValue = it.strTeam)
                        PropertyItem(propertyName = "League", propertyValue = it.strLeague)
                        PropertyItem(propertyName = "Gender", propertyValue = it.strGender)
                        PropertyItem(propertyName = "Formed Year", propertyValue = it.intFormedYear)
                        PropertyItem(propertyName = "Sport", propertyValue = it.strSport)
                        PropertyItem(propertyName = "Country", propertyValue = it.strCountry)
                        PropertyItem(propertyName = "Stadium", propertyValue = it.strStadium)
                        PropertyItem(
                            propertyName = "Stadium Capacity",
                            propertyValue = it.intStadiumCapacity
                        )
                        PropertyItem(
                            propertyName = "Stadium Location",
                            propertyValue = it.strStadiumLocation
                        )
                        Row(
                            modifier = Modifier.padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Team banner",
                                color = Color.Red,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            AsyncImage(
                                modifier = Modifier
                                    .size(Dimens.ArticleCardSize)
                                    .clip(MaterialTheme.shapes.large),
                                model = ImageRequest.Builder(context).data(it.strTeamBanner).build(),
                                contentDescription = null,
                                contentScale = ContentScale.Inside,
                            )

                        }
                        Row(
                            modifier = Modifier.padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "team badge",
                                color = Color.Red,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            AsyncImage(
                                modifier = Modifier
                                    .size(Dimens.ArticleCardSize)
                                    .clip(MaterialTheme.shapes.large),
                                model = ImageRequest.Builder(context).data(it.strTeamBadge).build(),
                                contentDescription = null,
                                contentScale = ContentScale.Inside,
                            )

                        }
                        Row(
                            modifier = Modifier.padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "team Jersey",
                                color = Color.Red,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            AsyncImage(
                                modifier = Modifier
                                    .size(Dimens.ArticleCardSize)
                                    .clip(MaterialTheme.shapes.large),
                                model = ImageRequest.Builder(context).data(it.strTeamJersey).build(),
                                contentDescription = null,
                                contentScale = ContentScale.Inside,
                            )

                        }

                        PropertyItem(
                            propertyName = "Description",
                            propertyValue = it.strDescriptionEN
                        )

                    }
                }
            }
        }

    }

}

@Composable
fun PropertyItem(propertyName: String, propertyValue: String?) {
    val annotatedText = buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontSize = 18.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
            )
        ) {
            append("$propertyName: ")
        }
        withStyle(
            style = SpanStyle(
                fontSize = 15.sp,
                color = Color.Gray
            )
        ) {
            append(propertyValue ?: "N/A")
        }
    }

    Text(text = annotatedText)
}