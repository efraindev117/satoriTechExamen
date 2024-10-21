package com.xsoft.satori.core.data.di

import com.xsoft.satori.core.data.IPokemonRepository
import com.xsoft.satori.core.data.PokemonRepositoryImpl
import com.xsoftcdmx.network.INetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNetworkDataSource(
        networkDataSourceImpl: PokemonRepositoryImpl
    ): INetworkDataSource

    @Binds
    abstract fun bindPokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): IPokemonRepository
}