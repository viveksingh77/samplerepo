package com.iswherevivek.sportsball.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.iswherevivek.sportsball.Dimens
import com.iswherevivek.sportsball.domain.model.Team
import com.iswherevivek.sportsball.presentation.SearchForClubs.PropertyItem

@Composable
fun ShowDetailsItems(
    modifier: Modifier= Modifier ,
    teams:Team
) {
    val context = LocalContext.current
    Column(modifier = modifier.padding(horizontal = 12.dp)) {

        teams.let {
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