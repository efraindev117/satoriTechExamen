package com.xsoftcdmx.database.model.pokemon

import com.xsoft.satori.core.model.pokemon.PokemonModel
import com.xsoft.satori.core.model.pokemon.Result

data class PokemonModelEntity(
    val count: Int? = 0,
    val next: String? = "",
    val previous: String? = "",
    val results: List<ResultEntity?>? = listOf()
)

fun ResultEntity.asUiModel() = Result(
    name = name,
    url = url,
)

fun PokemonModelEntity.asUiModel() = PokemonModel(
    count = count ?: 0,
    next = next ?: "",
    previous = previous ?: "",
    results = results?.map { it?.asUiModel() }
)