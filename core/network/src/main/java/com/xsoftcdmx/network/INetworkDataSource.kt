package com.xsoftcdmx.network

import com.xsoft.satori.core.common.Resource
import com.xsoftcdmx.network.model.detail.NetworkPokemonDetail
import com.xsoftcdmx.network.model.pokemon.NetworkPokemonModel
import com.xsoftcdmx.network.model.pokemon.NetworkResult
import kotlinx.coroutines.flow.Flow

interface INetworkDataSource {
    suspend fun getPokemon(limit: Int, offset: Int): Flow<Resource<NetworkPokemonModel>>
    suspend fun getPokemonDetail(id: Int): Flow<Resource<NetworkPokemonDetail>>
}
