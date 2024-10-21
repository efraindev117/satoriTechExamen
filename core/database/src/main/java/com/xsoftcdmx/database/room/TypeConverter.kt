package com.xsoftcdmx.database.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xsoftcdmx.database.model.pokemon.ResultEntity

class Converters {
    @TypeConverter
    fun fromResultList(results: List<ResultEntity?>?): String {
        return Gson().toJson(results)  // Puedes usar Gson o cualquier librería de serialización
    }

    @TypeConverter
    fun toResultList(resultsString: String): List<ResultEntity?>? {
        val listType = object : TypeToken<List<ResultEntity?>>() {}.type
        return Gson().fromJson(resultsString, listType)
    }
}