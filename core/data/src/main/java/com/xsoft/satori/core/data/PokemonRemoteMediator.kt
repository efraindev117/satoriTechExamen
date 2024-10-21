package com.xsoft.satori.core.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.xsoft.satori.core.data.model.asEntity
import com.xsoftcdmx.database.model.pokemon.PokemonModelEntity
import com.xsoftcdmx.database.model.pokemon.ResultEntity
import com.xsoftcdmx.database.room.PokemonDatabase
import com.xsoftcdmx.network.api.IApiService

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val apiService: IApiService,
    private val database: PokemonDatabase
) : RemoteMediator<Int, ResultEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ResultEntity>
    ): MediatorResult {
        val offset = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                    ?: return MediatorResult.Success(endOfPaginationReached = true)
                (lastItem.id / state.config.pageSize) * state.config.pageSize
            }
        }

        return try {
            val response = apiService.getPokemon(
                limit = state.config.pageSize,
                offset = offset
            )

            val results = response.body()?.results?.mapNotNull { it?.asEntity() } ?: emptyList()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.pokemonDao().clearAllPokemon()
                }
                database.pokemonDao().insertResults(results)
            }

            MediatorResult.Success(endOfPaginationReached = results.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

}

