package com.example.rickandmorty.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.Result
import com.example.rickandmorty.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ListAllCharacetrs() {

var characterList by remember {
    mutableStateOf(listOf<Character>())
}

    val callCharacetrList = RetrofitFactory()
        .getCharacterService()
        .getAllCharacters()

    callCharacetrList.enqueue(
        object : Callback<Result>{
            override fun onResponse(p0: Call<Result>, response: Response<Result>) {
                characterList = response.body()!!.results
            }

            override fun onFailure(p0: Call<Result>, p1: Throwable) {

            }

        }
    )

       Surface(
           modifier = Modifier.fillMaxSize(),
           color = Color(0xFF382222)
       ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Rick & Mory API",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
           LazyColumn {
               items(characterList){
                    characterCard(it)
               }
           }
       }
}

@Composable
fun characterCard(character: Character) {
    Card(
        modifier = Modifier
            .padding(bottom = 4.dp)
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x055CE8C2D)
        )
    ) {
        Row() {
            Card(
                modifier = Modifier.size(100.dp), colors = CardDefaults.cardColors(
                    containerColor  = Color.Magenta
                )
            ) {
                AsyncImage(model = character.image,
                    contentDescription = "")
            }
            Column(

                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp)
            ) {
                Text(text = character.name)
                Text(text = character.species)
            }
        }
    }
}

@Preview
@Composable
private fun listAllCharactersPreview() {
    ListAllCharacetrs()
}