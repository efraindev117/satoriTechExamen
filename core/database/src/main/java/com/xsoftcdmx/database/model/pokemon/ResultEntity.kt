package com.xsoftcdmx.database.model.pokemon

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result")
data class ResultEntity(
    @PrimaryKey val name: String,
    val url: String? = ""
)