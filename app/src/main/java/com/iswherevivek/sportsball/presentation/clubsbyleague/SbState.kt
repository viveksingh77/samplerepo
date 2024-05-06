package com.iswherevivek.sportsball.presentation.clubsbyleague

import com.iswherevivek.sportsball.domain.model.Team

data class SbState(
    val searchquery:String ="",
    val clubList: List<Team>? = emptyList()
)
