package com.xsoftcdmx.list_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.xsoft.satori.core.model.pokemon.PokemonModel
import com.xsoftcdmx.domain.GetPokemonPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonPagingUseCase: GetPokemonPagingUseCase
) : ViewModel() {

    val pokemonPagingFlow: Flow<PagingData<PokemonModel>> =
        getPokemonPagingUseCase().cachedIn(viewModelScope)
}

