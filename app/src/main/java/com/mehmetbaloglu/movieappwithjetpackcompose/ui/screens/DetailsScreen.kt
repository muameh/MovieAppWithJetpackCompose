package com.mehmetbaloglu.movieappwithjetpackcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.mehmetbaloglu.movieappwithjetpackcompose.data.model.Movie
import com.mehmetbaloglu.movieappwithjetpackcompose.data.model.getMovies


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, movieID: String?) {
    val currentMovie = getMovies().find { it.imdbID == movieID }
    //val currentMoviesList = getMovies().filter { it.imdbID == movieID }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable { navController.popBackStack() })
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Movies")
                }
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        )
    }) { paddingValues ->
        // İçerik alanı
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Text(text = "***${currentMovie?.title.toString()}***", modifier = Modifier.padding(16.dp))
            if (currentMovie != null) {
                HorizontalImageCard(movie = currentMovie)
                DetailedCard(currentMovie = currentMovie)
            }
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "GO BACK")
            }
        }
    }
}
@Composable
fun HorizontalImageCard(movie: Movie){
    LazyRow {
        items(movie.images?.filterNotNull()?: emptyList()){
            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .size(400.dp),
                elevation =CardDefaults.cardElevation(0.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = it),
                    contentDescription = "Movie poster",
                    modifier = Modifier.fillMaxSize()
                )
            }

        }
    }
}

@Composable
fun DetailedCard(currentMovie: Movie){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentMovie.title.toString(),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Director: ${currentMovie.director}",
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = "Released: ${currentMovie.released}",
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = "Genre: ${currentMovie.genre}",
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = "Country: ${currentMovie.country}",
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = "Imdb Rating: ${currentMovie.imdbRating}",
            style = MaterialTheme.typography.titleSmall
        )

        Text(
            text = "Runtime: ${currentMovie.runtime}",
            style = MaterialTheme.typography.titleSmall
        )
        HorizontalDivider(modifier = Modifier
            .width(100.dp)
            .padding(3.dp))
        Text(
            text = "Actors: ${currentMovie.actors}",
            style = MaterialTheme.typography.titleSmall,
        )
        HorizontalDivider(modifier = Modifier
            .width(100.dp)
            .padding(3.dp))
        Text(
            text = "Plot: ${currentMovie.plot}",
            style = MaterialTheme.typography.titleSmall,

        )


    }
}

@Preview
@Composable
fun showDetailScreen(){
    DetailsScreen(navController = rememberNavController(), movieID = "tt0499549")
}
