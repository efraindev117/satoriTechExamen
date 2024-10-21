package com.xsoft.satori.core.data.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.xsoft.satori.core.data.PokemonRemoteMediator
import com.xsoftcdmx.database.model.pokemon.PokemonModelEntity
import com.xsoftcdmx.database.room.PokemonDatabase
import com.xsoftcdmx.network.api.IApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonPager(
        pokemonDb: PokemonDatabase,
        pokemonApi: IApiService
    ): Pager<Int, PokemonModelEntity> {
        return Pager(
            config = PagingConfig(pageSize = 25),
            remoteMediator = PokemonRemoteMediator(
                apiService = pokemonApi,
                database = pokemonDb
            ), pagingSourceFactory = {
                pokemonDb.pokemonDao().getAllPokemon()
            }
        )
    }
}