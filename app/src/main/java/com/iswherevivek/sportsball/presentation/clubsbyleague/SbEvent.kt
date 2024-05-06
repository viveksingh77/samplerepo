package com.iswherevivek.sportsball.presentation.clubsbyleague

sealed class SbEvent {
    data class UpdateSearchQuery(val searchquery:String):SbEvent()
    object StoreToDb:SbEvent()
    object SearchClubs:SbEvent()

}