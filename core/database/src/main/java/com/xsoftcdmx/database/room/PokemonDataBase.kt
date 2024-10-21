package com.xsoftcdmx.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xsoftcdmx.database.model.pokemon.PokemonModelEntity
import com.xsoftcdmx.database.model.pokemon.ResultEntity


@Database(entities = [PokemonModelEntity::class, ResultEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)  // Agregamos el TypeConverter aquí
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): IPokemonDao

    companion object {
        @Volatile
        private var INSTANCE: PokemonDatabase? = null

        fun getDatabase(context: Context): PokemonDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonDatabase::class.java,
                    "pokemon_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}