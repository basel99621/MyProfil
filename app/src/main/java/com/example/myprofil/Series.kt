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
import coil.compose.AsyncImage
import com.example.premiereapp.SerieDetails
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun affichageSeriesSemaine(serieId: MutableStateFlow<String>,navController: NavController ,viewModel: MainViewModel) {

    val series by viewModel.series.collectAsState()


    if(series.isEmpty())   viewModel.getWeekSeries()



    LazyVerticalGrid(columns = GridCells.Fixed(2) ,
        verticalArrangement =
        Arrangement.spacedBy(16.dp),
        modifier=Modifier.background(Color.White),
        horizontalArrangement =
        Arrangement.spacedBy(16.dp)){

        items(series){

                series -> SerieCard(serieId, navController ,viewModel ,series)
        }
    }



}
@Composable
fun RechercheSeries(serieId: MutableStateFlow<String>,navController: NavController , name: String, viewModel: MainViewModel){
    val series by viewModel.series.collectAsState()
    var detail by remember {
        mutableStateOf(0)
    }
    var idacteurs by remember {
        mutableStateOf("")
    }
    if (series.isEmpty()) viewModel.searchSerie(name)
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(series){ serie -> SerieCard(serieId,navController ,viewModel  ,serie = serie)
        }

    }
}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SerieCard(serieId:MutableStateFlow<String>, navController: NavController,viewModel: MainViewModel ,serie: Serie) {



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
            .wrapContentHeight(),

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .clickable {
                    viewModel.rememberSelectedItem.value="Séries"
                    //detail = 1
                   // detailUpdate(detail)
                    serieId.value = serie.id.toString()
                  //  idserieUpdate(idserie)
                    navController.navigate("affichageSéries")
                }
            ,
            verticalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            // Image de couverture
            if (serie.backdrop_path == null){
                CoverImageSerie(imageUrl = "https://media.licdn.com/dms/image/C4D03AQHosBjWh4JapA/profile-displayphoto-shrink_800_800/0/1638740735138?e=1703116800&v=beta&t=AbBUBYo2U1Uhm3xs4dhWKV8ypsG_7jboIb-q5JuvgIM")
            } else {
                CoverImageSerie(imageUrl = serie.backdrop_path)
            }

            // Titre du film
            Text(
                text = serie.name,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun CoverImageSerie(imageUrl: String) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w342/$imageUrl",
        contentDescription = null,
        contentScale = ContentScale.FillHeight,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp) // Ajustez la hauteur de l'image en mode portrait
    )
}