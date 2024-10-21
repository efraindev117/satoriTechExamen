package com.xsoftcdmx.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.xsoft.satori.core.data.IPokemonRepository
import com.xsoft.satori.core.model.pokemon.Result
import com.xsoftcdmx.database.model.pokemon.asUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPokemonPagingUseCase @Inject constructor(
    private val pokemonRepository: IPokemonRepository
) {
    operator fun invoke(): Flow<PagingData<Result>> {
        return pokemonRepository.getPokemonPagingStream()
            .map { pagingData ->
                pagingData.map { it.asUiModel() }
            }
    }
}