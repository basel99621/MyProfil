import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@SuppressLint("SuspiciousIndentation", "RememberReturnType")
@Composable
fun FilmsDetails(viewModel: MainViewModel, id: String){


    affichageDetailsFilm(viewModel,id)



}
@SuppressLint("SuspiciousIndentation")
@Composable
fun affichageDetailsFilm(viewModel: MainViewModel, id: String){


    val moviedetails by viewModel.moviesDetails.collectAsState()

    viewModel.getFilmDetails(id)



            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ){
                item(span ={ GridItemSpan(maxCurrentLineSpan) }){
                    moviedetails.original_title?.let {
                        Text(
                            text = it,
                            style = TextStyle(
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

                item (span ={ GridItemSpan(maxCurrentLineSpan) }){
                    if (moviedetails.backdrop_path == null) {
                        CoverImageDetail(imageUrl = "https://votre-image-url.com")
                    } else {
                        CoverImageDetail(imageUrl = moviedetails.backdrop_path!!)
                    }
                }

                // Ajoutez un Spacer ici pour l'espace souhaité
                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    Spacer(modifier = Modifier.height(16.dp)) // Vous pouvez ajuster la hauteur selon vos besoins
                }

                item() {
                    if (moviedetails.poster_path== null) {
                        CoverImageDetail(imageUrl = urldefaulft)
                    } else {
                        CoverImageDetail(imageUrl = moviedetails.poster_path!!)
                    }
                }
                item {
                    Column {
                        moviedetails.release_date?.let { Text(text = it, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Light)) }
                        val genres = moviedetails.genres?: listOf()
                        Text(
                            text = buildGenresText(genres),
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic)
                        )
                    }
                }
                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    Spacer(modifier = Modifier.height(16.dp)) // Vous pouvez ajuster la hauteur selon vos besoins
                }
                item (span ={ GridItemSpan(maxCurrentLineSpan) }) {
                    Text(
                        text = "Synopsys",
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        ),)
                }
                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    Spacer(modifier = Modifier.height(16.dp)) // Vous pouvez ajuster la hauteur selon vos besoins
                }
                item (span ={ GridItemSpan(maxCurrentLineSpan) }) {
                    Text(
                        text = "${moviedetails.overview}",
                        style = TextStyle(
                            fontSize = 25.sp,
                        ),
                    )
                }
                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    Spacer(modifier = Modifier.height(16.dp)) // Vous pouvez ajuster la hauteur selon vos besoins
                }
                item (span ={ GridItemSpan(maxCurrentLineSpan) }) {
                    Text(
                        text = "Tête d'affiche",
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    Spacer(modifier = Modifier.height(16.dp)) // Vous pouvez ajuster la hauteur selon vos besoins
                }
                moviedetails.credits?.let {
                    items(it.cast){
                            acteur -> ActeursCardMovies(acteur = acteur)
                    }
                }


            }



    }


@Composable
fun ActeursCardMovies(acteur:Cast){
    ElevatedCard (
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
            ,
            verticalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            // Image de couverture
            if (acteur.profile_path == null){
                CoverImage(imageUrl = "https://media.licdn.com/dms/image/C4D03AQHosBjWh4JapA/profile-displayphoto-shrink_800_800/0/1638740735138?e=1703116800&v=beta&t=AbBUBYo2U1Uhm3xs4dhWKV8ypsG_7jboIb-q5JuvgIM")
            } else {
                CoverImage(imageUrl = acteur.profile_path)
            }
            // Titre du film
            acteur.original_name?.let {
                Text(
                    text = it,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }


        }
    }
}


@Composable
fun CoverImageDetail(imageUrl: String) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w780/$imageUrl",
        contentDescription = null,
        contentScale = ContentScale.FillHeight,
        modifier = Modifier
            .fillMaxWidth()
    )
}


var urldefaulft = "https://media.licdn.com/dms/image/C4D03AQHosBjWh4JapA/profile-displayphoto-shrink_800_800/0/1638740735138?e=1703116800&v=beta&t=AbBUBYo2U1Uhm3xs4dhWKV8ypsG_7jboIb-q5JuvgIM"
// Fonction pour construire la chaîne de texte des genres

fun buildGenresText(genres: List<Genre?>) : String{
    return if (genres.isNotEmpty()) {
        //   genres[0]?.name?.let { Log.v("3x", it) }
        genres.map { it?.name?:""}.joinToString(" & ")
    }else {
        ""
    }
}
