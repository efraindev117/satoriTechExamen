package com.xsoft.satori.core.data

import androidx.paging.PagingData
import com.xsoft.satori.core.common.Resource
import com.xsoftcdmx.database.model.pokemon.PokemonModelEntity
import com.xsoftcdmx.database.model.pokemon.ResultEntity
import com.xsoftcdmx.network.model.detail.NetworkPokemonDetail
import com.xsoftcdmx.network.model.pokemon.NetworkPokemonModel
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {
    fun getPokemonPagingStream(): Flow<PagingData<ResultEntity>>
    suspend fun getPokemon(limit: Int, offset: Int): Flow<Resource<NetworkPokemonModel>>
    suspend fun getPokemonDetail(id:Int): Flow<Resource<NetworkPokemonDetail>>
}
