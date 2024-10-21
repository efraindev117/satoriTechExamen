package com.xsoftcdmx.database.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.xsoftcdmx.database.model.pokemon.PokemonModelEntity
import com.xsoftcdmx.database.model.pokemon.ResultEntity

@Dao
interface IPokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResults(results: List<ResultEntity>)

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): PagingSource<Int, ResultEntity>

    @Query("SELECT * FROM pokemon WHERE id = :pokemonId")
    suspend fun getPokemonById(pokemonId: Int): ResultEntity?

    @Query("DELETE FROM pokemon")
    suspend fun clearAllPokemon()

    @Update
    suspend fun updatePokemon(pokemon: ResultEntity)
}