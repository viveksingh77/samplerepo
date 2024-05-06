package com.iswherevivek.sportsball.data.repository

import com.iswherevivek.sportsball.data.local.SportDao
import com.iswherevivek.sportsball.data.remote.SportsApi
import com.iswherevivek.sportsball.domain.model.ClubList
import com.iswherevivek.sportsball.domain.model.Team
import com.iswherevivek.sportsball.domain.repository.SportRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SportRepositoryImpl(
    private val sportsApi: SportsApi,
    private val sportDao: SportDao
):SportRepository {

    override fun searchClubs(searchquery: String): Flow<ClubList> {
        return flow {
            try {
                val clubList = sportsApi.getClubs(searchquery)
                emit(clubList)
            } catch (e: Exception) {
                throw SportRepositoryException("Error fetching club list: ${e.message}", e)
            }
        }
    }

    override fun getClubsFromRoom(): Flow<List<Team>> {
        return sportDao.getClubsFromRoom()
    }

    override fun getClubListFromRoom(): Flow<ClubList> {
        return sportDao.getClubListFromRoom()
    }

    override suspend fun upsertClub(club: Team) {
        sportDao.upsert(club)
    }

    override suspend fun upsertClubList(club: ClubList) {
        sportDao.upsertList(club)
    }

    override suspend fun getClub(search: String): ClubList {
        return sportsApi.getClub(search)
    }

    class SportRepositoryException(message: String, cause: Throwable? = null) : Exception(message, cause)

}