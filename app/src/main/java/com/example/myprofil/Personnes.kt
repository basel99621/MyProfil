import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
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


@Composable
fun affichageActeursSemaine(viewModel: MainViewModel, windowClass: WindowSizeClass) {

    val acteurs by viewModel.acteurs.collectAsState()

    if(acteurs.isEmpty())   viewModel.getWeekActeurs()
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {

            LazyVerticalGrid(columns = GridCells.Fixed(2) ,
                verticalArrangement =
                Arrangement.spacedBy(10.dp),
                modifier=Modifier.background(Color.White),
                horizontalArrangement =
                Arrangement.spacedBy(10.dp)){

                items(acteurs){
                        acteurs -> ActeursCard(acteurs)
                }
            }

        }
        else ->{

            LazyVerticalGrid(columns = GridCells.Fixed(3) ,
                verticalArrangement =
                Arrangement.spacedBy(10.dp),
                modifier=Modifier.background(Color.White),
                horizontalArrangement =
                Arrangement.spacedBy(10.dp)){

                items(acteurs){
                        acteurs -> ActeursCard(acteurs)
                }
            }

        }
    }




}



@Composable
fun ActeursCard(acteurs: Person) {
    var detail by remember {
        mutableStateOf(0)
    }

    var idacteur by remember {
        mutableStateOf("")
    }

    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colors.surface,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier

            .background(Color.White),

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .clickable {
                    detail = 1

                    idacteur = acteurs.id.toString()

                }
            ,
            verticalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            // Image de couverture
            if (acteurs.profile_path == null){
                CoverImageActeur(imageUrl = "https://media.licdn.com/dms/image/C4D03AQHosBjWh4JapA/profile-displayphoto-shrink_800_800/0/1638740735138?e=1703116800&v=beta&t=AbBUBYo2U1Uhm3xs4dhWKV8ypsG_7jboIb-q5JuvgIM")
            } else {
                CoverImageActeur(imageUrl = acteurs.profile_path)
            }

            // Le nom de l'acteur
            Text(
                text = acteurs.name,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun CoverImageActeur(imageUrl: String) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w342/$imageUrl",
        contentDescription = null,
        contentScale = ContentScale.FillHeight,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp) // Ajustez la hauteur de l'image en mode portrait
    )
}





@Composable
fun RechercheActeurs(name: String, viewModel: MainViewModel){
    val acteurs by viewModel.acteurs.collectAsState()
    var detail by remember {
        mutableStateOf(0)
    }
    var idacteurs by remember {
        mutableStateOf("")
    }
    if (acteurs.isEmpty()) viewModel.searchActeur(name)
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(acteurs){ acteurs -> ActeursCard(acteurs = acteurs)
        }

    }
}
