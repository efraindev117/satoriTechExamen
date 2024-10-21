package com.xsoft.satori.core.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.xsoft.satori.core.common.BaseRepository
import com.xsoft.satori.core.common.Resource
import com.xsoftcdmx.database.model.pokemon.PokemonModelEntity
import com.xsoftcdmx.database.model.pokemon.ResultEntity
import com.xsoftcdmx.database.room.PokemonDatabase
import com.xsoftcdmx.network.INetworkDataSource
import com.xsoftcdmx.network.api.IApiService
import com.xsoftcdmx.network.model.detail.NetworkPokemonDetail
import com.xsoftcdmx.network.model.pokemon.NetworkPokemonModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: IApiService,
    private val pokemonDatabase: PokemonDatabase
) : IPokemonRepository, INetworkDataSource, BaseRepository() {

    override suspend fun getPokemon(limit: Int, offset: Int): Flow<Resource<NetworkPokemonModel>> =
        safeApiCall {
            apiService.getPokemon(limit, offset)
        }

    override suspend fun getPokemonDetail(): Flow<Resource<NetworkPokemonDetail>> = safeApiCall {
        apiService.getPokemonDetail(id = 1)
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getPokemonPagingStream(): Flow<PagingData<ResultEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false,
                prefetchDistance = 5
            ),
            remoteMediator = PokemonRemoteMediator(
                apiService = apiService,
                database = pokemonDatabase
            ),
            pagingSourceFactory = { pokemonDatabase.pokemonDao().getAllPokemon() }
        ).flow
    }
}