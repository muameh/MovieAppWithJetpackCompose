package com.mehmetbaloglu.movieappwithjetpackcompose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mehmetbaloglu.movieappwithjetpackcompose.navigation.MovieScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Movies") },
            )
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            MainContent(navController)
        }
    }
}


@Composable
fun MainContent(
    navController: NavController, movieList: List<String> = listOf(
        "Avatar", "Harry Potter", "Titanic", "Star Wars", "Avengers", "Jurassic Park",
        "Avatar", "Harry Potter", "Titanic", "Star Wars", "Avengers", "Jurassic Park",
        "Avatar", "Harry Potter", "Titanic", "Star Wars", "Avengers", "Jurassic Park",
    )
) {
    LazyColumn {
        items(items = movieList) {
            MovieCard(movie = it) {
                navController.navigate(route = MovieScreens.DetailsScreen.screenName + "/$it")
            }
        }
    }
}


@Composable
fun MovieCard(movie: String, onItemClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onItemClick(movie) },
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Surface(
                modifier = Modifier
                    .padding(10.dp)
                    .wrapContentSize(),
                shape = RectangleShape,
                tonalElevation = 4.dp,

                ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = "movie image",
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = movie,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(4.dp)
                    )
                }

            }
        }
    }
}
