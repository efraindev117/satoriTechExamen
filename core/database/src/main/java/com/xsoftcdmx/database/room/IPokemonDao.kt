package com.xsoftcdmx.database.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xsoftcdmx.database.model.pokemon.PokemonModelEntity
import com.xsoftcdmx.database.model.pokemon.ResultEntity

@Dao
interface IPokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemons: List<PokemonModelEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResults(results: List<ResultEntity>)

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): PagingSource<Int, PokemonModelEntity>

    @Query("SELECT * FROM pokemon WHERE id = :pokemonId")
    suspend fun getPokemonById(pokemonId: Int): PokemonModelEntity?

    @Query("DELETE FROM pokemon")
    suspend fun clearAllPokemon()
}