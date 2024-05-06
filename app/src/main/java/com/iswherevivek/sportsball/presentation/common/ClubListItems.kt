package com.iswherevivek.sportsball.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.iswherevivek.sportsball.Dimens
import com.iswherevivek.sportsball.Dimens.ExtraSmallPadding
import com.iswherevivek.sportsball.R
import com.iswherevivek.sportsball.domain.model.ClubList
import com.iswherevivek.sportsball.domain.model.Team
import kotlinx.coroutines.flow.Flow


@Composable
fun ClubListItems(
    modifier: Modifier = Modifier,
    clubList: List<Team>,
) {
    if (clubList.isEmpty()) {
        EmptyScreen()
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
        contentPadding = PaddingValues(all = Dimens.ExtraSmallPadding)
    ) {
        items(count = clubList.size) {
            val club = clubList[it]
            ClubCards(club = club)
        }

    }
}

@Composable
fun ClubCards(
    modifier: Modifier = Modifier,
    club: Team
) {
    var isDescriptionVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column(modifier = modifier.clickable { isDescriptionVisible = !isDescriptionVisible }) {
        Row {
            AsyncImage(
                modifier = Modifier
                    .size(Dimens.ArticleCardSize)
                    .clip(MaterialTheme.shapes.large),
                model = ImageRequest.Builder(context).data(club.strTeamLogo).build(),
                contentDescription = null,
                contentScale = ContentScale.Inside
            )
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .padding(horizontal = ExtraSmallPadding)
                    .height(
                        Dimens.ArticleCardSize
                    )
            ) {
                Text(
                    text = club.strTeam,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.text_title),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = club.strDescriptionEN,
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(id = R.color.body),
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        if (isDescriptionVisible) {
            ShowDetailsItems(teams = club)
        }
    }

}
