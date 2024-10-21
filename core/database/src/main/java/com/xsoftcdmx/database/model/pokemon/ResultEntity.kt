package com.xsoftcdmx.database.model.pokemon

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class ResultEntity(
    val id: Int,
    @PrimaryKey
    val name: String,
    val url: String
)