package com.iswherevivek.sportsball.presentation.addleague

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.iswherevivek.sportsball.Dimens
import com.iswherevivek.sportsball.presentation.common.ClubListItems
import com.iswherevivek.sportsball.presentation.common.EmptyScreen
import com.iswherevivek.sportsball.presentation.common.ShowToast

@Composable
fun AddLeague(
    modifier: Modifier = Modifier,
    state: AddState
) {

    Scaffold(
        modifier = modifier
            .fillMaxSize()
        ,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Dimens.MediumPadding1) // Adjust vertical padding as needed
            ) {
                Text(
                    text = "Saved clubs/league",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            val list = state.teams?.teams
            if (list.isNullOrEmpty()) {
                EmptyScreen()
            }
            state.teams?.teams?.let {
                Log.d("CLUBLIST", "SbLeagueScreen: ${it}")
                ClubListItems(clubList = it)
            }
        }

//        ShowToast(message = "Showing all data from RoomDb")

    }
}
