package com.xsoftcdmx.list_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.xsoft.satori.core.common.Resource
import com.xsoftcdmx.list_detail.composable.convertHeightToMeters
import com.xsoftcdmx.list_detail.composable.convertWeightToKilograms
import com.xsoftcdmx.list_detail.composable.getPokemonTypeEmoji
import com.xsoftcdmx.network.model.detail.NetworkPokemonDetail
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    onPokemonClick: Int,
) {

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Detalles") })
    }) { paddingValues ->
        PokemonDetailScreenContent(paddingValues, onPokemonClick)
    }
}

@Composable
fun PokemonDetailScreenContent(
    paddingValues: PaddingValues, onPokemonClick: Int,
    mViewModel: PokemonViewModel = hiltViewModel()
) {
    LaunchedEffect(onPokemonClick) {
        mViewModel.getPokemonDetail(onPokemonClick)
    }

    val pokemonDetailState by mViewModel.pokemonDetailState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when (pokemonDetailState) {
            is Resource.Loading -> {
                CircularProgressIndicator()
            }

            is Resource.Success -> {
                val pokemonDetail =
                    (pokemonDetailState as Resource.Success<NetworkPokemonDetail>).data
                val pokemonType = pokemonDetail.types?.first()?.type?.name
                val emoji = getPokemonTypeEmoji(pokemonType)

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(pokemonDetail.sprites?.frontShiny)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Imagen del Pokémon ${pokemonDetail.name}",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .border(4.dp, MaterialTheme.colorScheme.primary, CircleShape)
                            .padding(8.dp)
                    )

                    Text(
                        text = pokemonDetail.name!!.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        },
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Altura: ${convertHeightToMeters(pokemonDetail.height!!)}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = "Peso: ${convertWeightToKilograms(pokemonDetail.weight!!)}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = "Tipo: $emoji ${pokemonDetail.types?.first()?.type?.name?.capitalize()}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            is Resource.Failure -> {
                Text(
                    text = "Error al cargar los detalles",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
            }
            is Resource.Failure -> TODO()
        }
    }
}
