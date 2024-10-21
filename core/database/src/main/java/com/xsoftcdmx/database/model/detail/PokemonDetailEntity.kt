package com.xsoftcdmx.database.model.detail

data class PokemonDetailEntity(
    val id: Int?,
    val height: Int?,
    val is_default: Boolean?,
    val name: String?,
    val order: Int?,
    val sprites: SpritesEntity?,
    val types: List<TypeEntity>?,
    val weight: Int?
)