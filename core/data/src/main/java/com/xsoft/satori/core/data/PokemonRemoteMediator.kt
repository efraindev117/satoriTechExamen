package com.xsoft.satori.core.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.xsoftcdmx.database.model.pokemon.PokemonModelEntity
import com.xsoftcdmx.database.model.pokemon.ResultEntity
import com.xsoftcdmx.database.room.PokemonDatabase
import com.xsoftcdmx.network.api.IApiService

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val apiService: IApiService,
    private val database: PokemonDatabase
) : RemoteMediator<Int, PokemonModelEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonModelEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                lastItem?.id?.toInt()
                    ?: return MediatorResult.Success(endOfPaginationReached = true)
            }
        }
        try {
            val apiResponse = apiService.getPokemon(limit = state.config.pageSize, offset = page)

            val pokemon = apiResponse.body()?.results!!.map {
                PokemonModelEntity(
                    count = apiResponse.body()?.count ?: 0,
                    next = apiResponse.body()?.next ?: "",
                    previous = apiResponse.body()?.previous.toString() ?: "",
                    results = listOf(ResultEntity(name = it?.name ?: "", url = it?.url ?: ""))
                )
            } ?: listOf()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.pokemonDao().clearAllPokemon()
                }
                database.pokemonDao().insertPokemon(pokemon)
            }
            return MediatorResult.Success(endOfPaginationReached = apiResponse.body()?.next == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
}