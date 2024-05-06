package com.iswherevivek.sportsball.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iswherevivek.sportsball.domain.model.ClubList
import com.iswherevivek.sportsball.domain.model.Team

@Database(entities = [Team::class , ClubList::class] , version = 1)
@TypeConverters(SportTypeConverter::class)
abstract class SportsDb:RoomDatabase() {
    abstract val sportDao:SportDao
}