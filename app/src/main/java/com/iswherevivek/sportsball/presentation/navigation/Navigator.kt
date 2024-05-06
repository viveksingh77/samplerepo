package com.iswherevivek.sportsball.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iswherevivek.sportsball.R
import com.iswherevivek.sportsball.presentation.SearchForClubs.SearchForClubs
import com.iswherevivek.sportsball.presentation.SearchForClubs.SfcViewmodel
import com.iswherevivek.sportsball.presentation.addleague.AddLeague
import com.iswherevivek.sportsball.presentation.addleague.AddLeagueViewmodel
import com.iswherevivek.sportsball.presentation.clubsbyleague.SbLeagueScreen
import com.iswherevivek.sportsball.presentation.clubsbyleague.SbLeagueViewmodel

@Composable
fun Navigator() {
    val bottomNavigatorItems = remember {
        listOf(
            BottomNavigationItem(
                icon = R.drawable.baseline_sports_soccer_24,
                text = "Clubs/leagues"
            ),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.baseline_cloud_download_24, text = "Saved"),
        )
    }
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.OnClubByLeague.route -> 0
            Route.OnSearchClub.route -> 1
            Route.OnAddLeague.route -> 2
            else -> 0
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation(
                items = bottomNavigatorItems,
                selected = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.OnClubByLeague.route
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.OnSearchClub.route
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = Route.OnAddLeague.route
                        )
                    }
                })
        }

    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.OnClubByLeague.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ){
            composable(route = Route.OnClubByLeague.route) {
                val viewmodel: SbLeagueViewmodel = hiltViewModel()
                val state = viewmodel.state.value
                val snackbarevent = viewmodel.snackBarEventFlow
                SbLeagueScreen(state =state , event =viewmodel::onEvent , snackBarEvent = snackbarevent)
            }
            composable(route = Route.OnSearchClub.route) {
                val viewmodel: SfcViewmodel = hiltViewModel()
                val state = viewmodel.state.value
                val snackbarevent = viewmodel.snackBarEventFlow
                SearchForClubs(state =state , event =viewmodel::onEvent)
            }
            composable(route = Route.OnAddLeague.route) {
                val viewmodel: AddLeagueViewmodel = hiltViewModel()
                val state = viewmodel.state.value
                AddLeague(state =state)
            }
        }
    }
}
private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}