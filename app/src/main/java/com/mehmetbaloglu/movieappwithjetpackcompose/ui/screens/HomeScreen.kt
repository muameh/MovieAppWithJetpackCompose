package com.mehmetbaloglu.movieappwithjetpackcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mehmetbaloglu.movieappwithjetpackcompose.data.model.Movie
import com.mehmetbaloglu.movieappwithjetpackcompose.data.model.getMovies
import com.mehmetbaloglu.movieappwithjetpackcompose.ui.navigation.MovieScreens

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
    navController: NavController, movieList: List<Movie> = getMovies()
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
fun MovieCard(movie: Movie, onItemClick: (String) -> Unit = {}) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            //.height(100.dp)
            .clickable { onItemClick(movie.imdbID.toString()) },
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Surface(
                modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth(),
                shape = RectangleShape,
                tonalElevation = 4.dp,

                ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    //Coil Usage
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = movie.images?.get(0),
                        ),
                        contentDescription = "Movie poster",
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = movie.title.toString(),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Director: ${movie.director}",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "Released: ${movie.released}",
                            style = MaterialTheme.typography.titleSmall
                        )
                        if (expanded) {
                            Column {
                                Text(
                                    text = "Genre: ${movie.genre}",
                                    style = MaterialTheme.typography.titleSmall
                                )
                                Text(
                                    text = "Country: ${movie.country}",
                                    style = MaterialTheme.typography.titleSmall
                                )
                                Text(
                                    text = "Imdb Rating: ${movie.imdbRating}",
                                    style = MaterialTheme.typography.titleSmall
                                )

                                Text(
                                    text = "Runtime: ${movie.runtime}",
                                    style = MaterialTheme.typography.titleSmall
                                )
                                HorizontalDivider(modifier = Modifier.width(100.dp).padding(3.dp))
                                Text(
                                    text = "Actors: ${movie.actors}",
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier,
                                    overflow = TextOverflow.Ellipsis,
                                    //maxLines = 2
                                )
                                HorizontalDivider(modifier = Modifier.width(100.dp).padding(3.dp))
                                Text(
                                    text = "Plot: ${movie.plot}",
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier,
                                    overflow = TextOverflow.Ellipsis,
                                    //maxLines = 2
                                )
                            }
                        }
                        Icon(
                            imageVector = if (expanded) {
                                Icons.Filled.KeyboardArrowUp
                            } else {
                                Icons.Filled.KeyboardArrowDown
                            },
                            contentDescription = "Down Arrow",
                            modifier = Modifier
                                .padding(10.dp)
                                .size(25.dp)
                                .clickable { expanded = !expanded },
                            tint = Color.Red
                        )
                    }
                    // Spacer to push the Icon to the right
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview
@Composable
fun showcard() {
    MovieCard(movie = getMovies()[0]) {}
}

