package com.xsoftcdmx.list_detail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.xsoftcdmx.list_detail.composable.PokemonList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen(modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Pokemons") })
    }, contentWindowInsets = WindowInsets.ime) { paddingValues ->
        PokemonScreenContent(modifier, paddingValues)
    }
}

@Composable
fun PokemonScreenContent(
    modifier: Modifier,
    paddingValues: PaddingValues,
    mViewModel: PokemonViewModel = hiltViewModel()
) {
    val lazyPagingItems = mViewModel.pokemonPagingFlow.collectAsLazyPagingItems()
    PokemonList(
        modifier = modifier.padding(paddingValues = paddingValues),
        lazyPagingItems = lazyPagingItems
    )

}

