package com.xsoftcdmx.list_detail.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.xsoft.satori.core.model.pokemon.Result
import com.xsoftcdmx.ui.CircularImage


@Composable
fun PokemonList(
    modifier: Modifier = Modifier,
    lazyPagingItems: LazyPagingItems<Result>,
    goToDetail: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(lazyPagingItems.itemCount) { index ->
            val pokemon = lazyPagingItems[index]
            pokemon?.let {
                ListItem(
                    name = it.name,
                    spriteUrl = it.sprite(),
                    id = it.getIdFromUrl().toInt(),
                    goToDetail = goToDetail
                )
            }
        }

        lazyPagingItems.apply {
            when {
                loadState.refresh is androidx.paging.LoadState.Loading -> {
                    item {
                        LinearProgressIndicator(
                            modifier = Modifier.fillParentMaxWidth(),
                            trackColor = MaterialTheme.colorScheme.primary
                        )
                    }
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
fun ListItem(
    name: String,
    id: Int,
    spriteUrl: String,
    modifier: Modifier = Modifier,
    goToDetail: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .clickable {
                goToDetail(id)
            }
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        CircularImage(
            imageUrl = spriteUrl,
            text = name,
            placeholder = {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color.Gray, shape = CircleShape)
                )
            },
            backgroundColor = MaterialTheme.colorScheme.primary,
            textColor = Color.White,
            modifier = Modifier.size(64.dp)
        )
        Text(
            text = name,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        IconButton(onClick = { /*TODO*/ }) {

        }
    }
}