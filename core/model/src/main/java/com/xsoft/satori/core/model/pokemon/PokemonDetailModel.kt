package com.xsoft.satori.core.model.pokemon

data class PokemonDetail(
    val id: Int?,
    val height: Int?,
    val name: String?,
    val order: Int?,
    val sprites: Sprites?,
    val weight: Int?
)

data class Sprites(
    val back_default: String? = "",
    val back_female: Any? = null,
    val back_shiny: String? = "",
    val back_shiny_female: Any? = null,
    val front_default: String? = "",
    val front_female: Any? = null,
    val front_shiny: String? = "",
    val front_shiny_female: Any? = null
)
