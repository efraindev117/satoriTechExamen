package com.xsoftcdmx.domain

import com.xsoft.satori.core.data.IPokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideGetPokemonPagingUseCase(
        pokemonRepository: IPokemonRepository
    ): GetPokemonPagingUseCase {
        return GetPokemonPagingUseCase(pokemonRepository)
    }
}