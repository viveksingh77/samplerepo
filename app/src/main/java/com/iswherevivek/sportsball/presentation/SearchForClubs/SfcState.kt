package com.iswherevivek.sportsball.presentation.SearchForClubs

import com.iswherevivek.sportsball.domain.model.Team

data class SfcState(
    val searchquery:String="",
    val club:Team? = null
)
