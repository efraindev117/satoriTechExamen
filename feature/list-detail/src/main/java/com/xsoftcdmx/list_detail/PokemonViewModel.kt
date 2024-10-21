package com.xsoftcdmx.list_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.xsoft.satori.core.common.Resource
import com.xsoft.satori.core.model.pokemon.Result
import com.xsoftcdmx.domain.GetPokemonPagingUseCase
import com.xsoftcdmx.network.model.detail.NetworkPokemonDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonPagingUseCase: GetPokemonPagingUseCase
) : ViewModel() {

    val pokemonPagingFlow: Flow<PagingData<Result>> =
        getPokemonPagingUseCase().cachedIn(viewModelScope)

    private val _pokemonDetailState =
        MutableStateFlow<Resource<NetworkPokemonDetail>>(Resource.Loading)
    val pokemonDetailState: StateFlow<Resource<NetworkPokemonDetail>> =
        _pokemonDetailState.asStateFlow()


    fun getPokemonDetail(id: Int) {
        viewModelScope.launch {
            getPokemonPagingUseCase(id).collect { resource ->
                _pokemonDetailState.value = resource
            }
        }
    }

}

