package com.xsoft.satori.core.data

import androidx.paging.PagingData
import com.xsoftcdmx.database.model.pokemon.PokemonModelEntity
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {
    fun getPokemonPagingStream(): Flow<PagingData<PokemonModelEntity>>
}
