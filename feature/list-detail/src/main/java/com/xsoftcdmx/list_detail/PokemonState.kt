package com.xsoftcdmx.list_detail

import androidx.paging.PagingData
import com.xsoft.satori.core.model.pokemon.PokemonModel

data class PokemonState(
    val pokemon: PagingData<PokemonModel> = PagingData.empty(),
    val isLoading: Boolean = false,
    val error: String? = null
)