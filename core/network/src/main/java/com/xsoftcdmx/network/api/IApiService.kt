package com.xsoftcdmx.network.api

import com.xsoftcdmx.network.model.detail.NetworkPokemonDetail
import com.xsoftcdmx.network.model.pokemon.NetworkPokemonModel
import com.xsoftcdmx.network.model.pokemon.NetworkResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IApiService {
    @GET("pokemon")
    suspend fun getPokemon(
        @Query("limit") limit: Int = 25,
        @Query("offset") offset: Int = 0
    ): Response<NetworkPokemonModel>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): Response<NetworkPokemonDetail>
}