import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun Film(filmId: MutableStateFlow<String>, navController: NavController, viewModel: MainViewModel){

    affichageFilmsSemaine(filmId,navController ,viewModel)
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun affichageFilmsSemaine(filmId: MutableStateFlow<String>, navController: NavController, viewModel: MainViewModel) {



    val movies by viewModel.movies.collectAsState()




        if (movies.isEmpty()) viewModel.getWeekMovies()

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),

                ) { items(movies){ movie ->

                MovieCard(filmId ,navController  , movie = movie)
            }
        }









}




@Composable
fun MovieCard(filmId: MutableStateFlow<String>,
              navController: NavController,
              movie: Movie) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colors.surface,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .wrapContentHeight()
            .background(Color.White),

        ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .clickable {
                    filmId.value = movie.id.toString()
                    navController.navigate("filmDetails")
                },
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Image de couverture
            if (movie.backdrop_path == null){
                CoverImage(imageUrl = "https://media.licdn.com/dms/image/C4D03AQHosBjWh4JapA/profile-displayphoto-shrink_800_800/0/1638740735138?e=1703116800&v=beta&t=AbBUBYo2U1Uhm3xs4dhWKV8ypsG_7jboIb-q5JuvgIM")
            } else {
                CoverImage(imageUrl = movie.backdrop_path)
            }

            // Titre du film
            Text(
                text = movie.original_title,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            // Date de publication
            Text(
                text = " ${movie.release_date}",
                style = TextStyle(fontSize = 17.sp)
            )
        }
    }
}



@Composable
fun CoverImage(imageUrl: String) {

    AsyncImage(
        model = "https://image.tmdb.org/t/p/w342/$imageUrl",
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()

            .height(200.dp)
        // Ajustez la hauteur de l'image en mode portrait
    )

}


@SuppressLint("SuspiciousIndentation")
@Composable
fun RechercheFilms(filmId: MutableStateFlow<String>, navController: NavController, name: String, viewModel: MainViewModel){
    val movies by viewModel.movies.collectAsState()


        if (movies.isEmpty()) viewModel.searchMovies(name)
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies){ movie -> MovieCard(filmId ,navController ,
                movie = movie)
            }

        }

}


