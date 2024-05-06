package com.iswherevivek.sportsball

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.iswherevivek.sportsball.presentation.SearchForClubs.SearchForClubs
import com.iswherevivek.sportsball.presentation.SearchForClubs.SfcViewmodel
import com.iswherevivek.sportsball.presentation.addleague.AddLeague
import com.iswherevivek.sportsball.presentation.addleague.AddLeagueViewmodel
import com.iswherevivek.sportsball.presentation.clubsbyleague.SbLeagueScreen
import com.iswherevivek.sportsball.presentation.clubsbyleague.SbLeagueViewmodel
import com.iswherevivek.sportsball.presentation.common.ShowToast
import com.iswherevivek.sportsball.presentation.navigation.Navigator
import com.iswherevivek.sportsball.ui.theme.SportsBallTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportsBallTheme {
                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()

                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode
                    )
                }
                Navigator()



//                val viewmodel: SbLeagueViewmodel = hiltViewModel()
//                val state = viewmodel.state.value
//                SbLeagueScreen(
//                    state = state,
//                    event = viewmodel::onEvent,
//                    snackBarEvent = viewmodel.snackBarEventFlow
//                )

//                val viewmodel: SfcViewmodel = hiltViewModel()
//                val state = viewmodel.state.value
//                SearchForClubs(state = state, event = viewmodel::onEvent)

//                    val viewmodel:AddLeagueViewmodel = hiltViewModel()
//                    val state = viewmodel.state.value
//                    AddLeague(state = state)

            }
        }
    }
}