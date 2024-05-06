package com.iswherevivek.sportsball.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iswherevivek.sportsball.domain.model.ClubList
import com.iswherevivek.sportsball.domain.model.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface SportDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(clubs:Team)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertList(clubs:ClubList)

    @Query("SELECT * FROM team")
    fun getClubsFromRoom():Flow<List<Team>>

    @Query("SELECT * FROM ClubList")
    fun getClubListFromRoom():Flow<ClubList>
}