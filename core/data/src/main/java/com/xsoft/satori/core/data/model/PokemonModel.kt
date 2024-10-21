package com.xsoft.satori.core.data.model

import com.xsoftcdmx.database.model.pokemon.PokemonModelEntity
import com.xsoftcdmx.database.model.pokemon.ResultEntity
import com.xsoftcdmx.network.model.pokemon.NetworkPokemonModel
import com.xsoftcdmx.network.model.pokemon.NetworkResult

fun NetworkResult.asEntity(): ResultEntity {
    val safeName = name ?: "Unknown"
    val safeUrl = url ?: ""
    val safeId = getIdFromUrl() ?: 0

    return ResultEntity(
        id = safeId,
        name = safeName,
        url = safeUrl
    )
}
fun NetworkPokemonModel.asEntity() = PokemonModelEntity(
    count = count,
    next = next,
    previous = previous.toString(),
    results = results?.map { it?.asEntity() }
)
