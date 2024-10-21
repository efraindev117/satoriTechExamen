package com.xsoft.satori

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.xsoft.satori.navigation.RootHost
import com.xsoftcdmx.designsystem.theme.SatoriTheme
import com.xsoftcdmx.list_detail.PokemonScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SatoriTheme {
                RootHost()
            }
        }
    }
}


