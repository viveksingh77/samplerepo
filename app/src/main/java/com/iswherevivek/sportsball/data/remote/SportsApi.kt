package com.iswherevivek.sportsball.data.remote

import com.iswherevivek.sportsball.domain.model.ClubList
import com.iswherevivek.sportsball.domain.model.Team
import retrofit2.http.GET
import retrofit2.http.Query


interface SportsApi {
    @GET("search_all_teams.php")
    suspend fun getClubs(
        @Query("l") searchquery:String
    ): ClubList

    @GET("searchteams.php")
    suspend fun getClub(
        @Query("t") search:String
    ):ClubList

}
