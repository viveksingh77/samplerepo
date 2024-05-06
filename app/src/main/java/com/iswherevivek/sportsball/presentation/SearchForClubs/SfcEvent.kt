package com.iswherevivek.sportsball.presentation.SearchForClubs

sealed class SfcEvent {
    object SearchClub :SfcEvent()
    data class UpdateSearchQuery(val searchQuery:String):SfcEvent()
}