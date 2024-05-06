package com.iswherevivek.sportsball.presentation.clubsbyleague

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.iswherevivek.sportsball.Dimens.MediumPadding1
import com.iswherevivek.sportsball.presentation.common.ClubListItems
import com.iswherevivek.sportsball.presentation.common.SearchBar
import com.iswherevivek.sportsball.presentation.common.ShowToast
import com.iswherevivek.sportsball.utils.SnackBarEvent
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest


@Composable
fun SbLeagueScreen(
    state: SbState,
    event: (SbEvent) -> Unit,
    snackBarEvent: SharedFlow<SnackBarEvent>
) {
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = true) {
        snackBarEvent.collectLatest {
            when (it) {
                is SnackBarEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = it.message,
                        duration = it.duration
                    )
                }

                SnackBarEvent.NavigateUp -> {}
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            item {
                SearchBar(
                    text = state.searchquery,
                    readonly = false,
                    onValueChange = { event(SbEvent.UpdateSearchQuery(it)) },
                    onSearch = { event(SbEvent.SearchClubs) },
                    hint = "English premier league"
                )
            }
            item {
                if (state.clubList?.isNotEmpty() == true) {
                    Button(
                        modifier = Modifier.fillMaxWidth(), onClick = {
                            Toast.makeText(
                                context,
                                "Saved to Room DB",
                                Toast.LENGTH_SHORT
                            ).show()
                            event(SbEvent.StoreToDb)
                        }) {
                        Text(text = "Add All to DB")
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(MediumPadding1))
            }
        }
        Column(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxSize()
        ) {
            state.clubList?.let {
                Log.d("CLUBLIST", "SbLeagueScreen: ${it}")
                ClubListItems(clubList = it)
            }
        }

    }


}