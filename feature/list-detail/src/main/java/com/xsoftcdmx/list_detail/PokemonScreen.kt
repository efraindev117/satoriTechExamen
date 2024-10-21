package com.xsoftcdmx.list_detail

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.xsoftcdmx.list_detail.composable.PokemonList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen(modifier: Modifier = Modifier, onPokemonClick: (Int) -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Pokemons") })
    }, contentWindowInsets = WindowInsets.ime) { paddingValues ->
        PokemonScreenContent(modifier, paddingValues, goToDetail = onPokemonClick)
    }
}

@Composable
fun PokemonScreenContent(
    modifier: Modifier,
    paddingValues: PaddingValues,
    mViewModel: PokemonViewModel = hiltViewModel(),
    goToDetail: (Int) -> Unit

) {
    val lazyPagingItems = mViewModel.pokemonPagingFlow.collectAsLazyPagingItems()

    LaunchedEffect(lazyPagingItems) {
        snapshotFlow { lazyPagingItems.loadState }
            .collect { loadState ->
                if (loadState.refresh is LoadState.Error) {
                    Log.e(
                        "PokemonScreenContent",
                        "Error: ${(loadState.refresh as LoadState.Error).error}"
                    )
                }
            }
    }


    PokemonList(
        modifier = modifier.padding(paddingValues = paddingValues),
        lazyPagingItems = lazyPagingItems,
        goToDetail = goToDetail
    )

}

