package com.xsoftcdmx.list_detail.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberImagePainter
import com.xsoft.satori.core.model.pokemon.PokemonModel

@Composable
fun PokemonList(
    modifier: Modifier = Modifier,
    lazyPagingItems: LazyPagingItems<PokemonModel>
) {

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(lazyPagingItems.itemCount) { index ->
            val pokemonModel = lazyPagingItems[index]
            pokemonModel?.results?.forEach { result ->
                result?.let { pokemon ->
                    ListItem(
                        name = pokemon.name ?: "Unknown Pokémon",
                        spriteUrl = pokemon.sprite()
                    )
                }
            }
        }

        lazyPagingItems.apply {
            when {
                loadState.refresh is androidx.paging.LoadState.Loading -> {
                    item { CircularProgressIndicator() }
                }

                loadState.append is androidx.paging.LoadState.Loading -> {
                    item { CircularProgressIndicator() }
                }

                loadState.refresh is androidx.paging.LoadState.Error -> {
                    val error = (loadState.refresh as androidx.paging.LoadState.Error).error
                    item { Text(text = "Error: ${error.localizedMessage}") }
                }
            }
        }
    }

}

@Composable
fun ListItem(name: String, spriteUrl: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        AsyncImage(
            model = spriteUrl,
            contentDescription = name,
            modifier = Modifier.size(64.dp)
        )
        // Nombre del Pokémon
        Text(
            text = name,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}