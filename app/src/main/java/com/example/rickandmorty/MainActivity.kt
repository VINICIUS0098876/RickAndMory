package com.example.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.rickandmorty.screens.CharacterDetails
import com.example.rickandmorty.screens.ListAllCharacetrs
import com.example.rickandmorty.ui.theme.RickAndMortyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                //CharacterDetails()
                ListAllCharacetrs()
            }
        }
    }
}

