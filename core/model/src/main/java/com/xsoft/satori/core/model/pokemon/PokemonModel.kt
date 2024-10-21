package com.xsoft.satori.core.model.pokemon

data class PokemonModel(
    val count: Int?,
    val next: String?,
    val previous: Any?,
    val results: List<Result?>?
)