package com.xsoftcdmx.list_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.xsoft.satori.core.model.pokemon.Result
import com.xsoftcdmx.domain.GetPokemonPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    getPokemonPagingUseCase: GetPokemonPagingUseCase
) : ViewModel() {

    val pokemonPagingFlow: Flow<PagingData<Result>> = flow {
        // Introduce un retraso de 20 segundos
        delay(25_000L) // 20 segundos en milisegundos
        // Luego emite el flujo del use case
        emitAll(getPokemonPagingUseCase())
    }.cachedIn(viewModelScope)
}

