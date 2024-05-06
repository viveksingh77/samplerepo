package com.iswherevivek.sportsball.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iswherevivek.sportsball.domain.model.Team


@ProvidedTypeConverter
class SportTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun teamListToString(teamList: List<Team>?): String {
        return gson.toJson(teamList)
    }

    @TypeConverter
    fun stringToTeamList(data: String): List<Team> {
        val listType = object : TypeToken<List<Team>>() {}.type
        return gson.fromJson(data, listType)
    }
}