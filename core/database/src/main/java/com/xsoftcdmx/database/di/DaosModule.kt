package com.xsoftcdmx.database.di

import com.xsoftcdmx.database.room.IPokemonDao
import com.xsoftcdmx.database.room.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun providePokemonDao(database: PokemonDatabase): IPokemonDao {
        return database.pokemonDao()
    }
}