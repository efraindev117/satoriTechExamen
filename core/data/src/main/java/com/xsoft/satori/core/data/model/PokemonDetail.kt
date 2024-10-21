package com.xsoft.satori.core.data.model

import com.xsoftcdmx.database.model.detail.PokemonDetailEntity
import com.xsoftcdmx.database.model.detail.SpritesEntity
import com.xsoftcdmx.database.model.detail.TypeEntity
import com.xsoftcdmx.database.model.detail.TypeEntityX
import com.xsoftcdmx.network.model.detail.NetworkPokemonDetail
import com.xsoftcdmx.network.model.detail.NetworkSprites
import com.xsoftcdmx.network.model.detail.NetworkType
import com.xsoftcdmx.network.model.detail.NetworkTypeX

fun NetworkSprites.asEntity() = SpritesEntity(
    back_default = backDefault,
    back_female = backFemale,
    back_shiny = backShiny,
    back_shiny_female = backShinyFemale,
    front_default = frontDefault,
    front_female = frontFemale,
    front_shiny_female = frontShinyFemale,
    front_shiny = frontShiny
)

fun NetworkTypeX.asEntity() = TypeEntityX(
    name = name,
    url = url
)

fun NetworkType.asEntity() = TypeEntity(
    slot = slot,
    type = type?.asEntity()
)

fun NetworkPokemonDetail.asEntity() {
    PokemonDetailEntity(
        id = id,
        height = height,
        is_default = isDefault,
        name = name,
        order = order,
        weight = weight,
        sprites = sprites?.asEntity(),
        types = types?.map { it.asEntity() }
    )
}