package com.iswherevivek.sportsball.domain.repository

import com.iswherevivek.sportsball.domain.model.ClubList
import com.iswherevivek.sportsball.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface SportRepository {
    fun searchClubs(searchquery:String):Flow<ClubList>

    fun getClubsFromRoom():Flow<List<Team>>
    fun getClubListFromRoom():Flow<ClubList>

    suspend fun upsertClub(club:Team)
    suspend fun upsertClubList(club:ClubList)

    suspend fun getClub(search:String):ClubList
}