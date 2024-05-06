package com.iswherevivek.sportsball.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ClubList(
    @PrimaryKey(autoGenerate = true)
    val id:Long?=null,
    val teams:List<Team>
)
