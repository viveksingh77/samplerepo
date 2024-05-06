package com.iswherevivek.sportsball.presentation.navigation

sealed class Route(
    val route:String
) {
    object OnClubByLeague:Route(route = "clubByLeague")
    object OnAddLeague:Route(route = "addLeague")
    object OnSearchClub:Route(route = "searchClub")
}