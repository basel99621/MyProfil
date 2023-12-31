import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myprofil.R

@Composable
fun Screen(windowClass: WindowSizeClass, navController: NavController, viewModel: MainViewModel) {
    when (windowClass.widthSizeClass) {

        WindowWidthSizeClass.Compact -> {
            LayoutVert(navController, viewModel)
        }


        else -> {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {


                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(
                        modifier = Modifier.height(30.dp)
                    )

                    Image(
                        painterResource(R.drawable.basel),
                        contentDescription = "photo de profil",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .border(3.dp, Color.Black, CircleShape)

                    )
                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        textAlign = TextAlign.Justify,
                        text = "Etudiant en alternance ",
                        fontSize = 20.sp
                    )
                    Text(
                        textAlign = TextAlign.Justify, text =
                        "à l'école d'ingénieurs ISIS",
                        fontSize = 20.sp
                    )
                }



                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = "Nom : AL KHATIB",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    )
                    Text(
                        text = "Prénom : BASEL",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    )
                    Text(
                        text = "Adresse mail : ",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Gray
                        )
                    )
                    Text(
                        text = "basil.kh9957@gmail.com",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Gray
                        )
                    )
                    Text(
                        text = "Téléphone : 06593077687",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Gray
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    button(navController , contenu ="submit", viewModel )
                }
            }
        }


    }
}
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun button(navController: NavController, contenu: String, viewModel: MainViewModel) {


    viewModel.iconeVisible.value=true
       Button(onClick = { navController.navigate("films")
           viewModel.rememberSelectedItemPortrait.value="Films"
           viewModel.rememberSelectedItem.value="Films"
        }) {
            Text("Démarer")
        }





}

@Composable
fun LayoutVert(navController: NavController, viewModel: MainViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Image(
            painterResource(R.drawable.basel),
            contentDescription = "photo de profil",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(3.dp, Color.Black, CircleShape)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            textAlign = TextAlign.Justify, text = "Etudiant en alternance ",
            fontSize = 20.sp
        )
        Text(
            textAlign = TextAlign.Justify, text =
            "à l'école d'ingénieurs ISIS",
            fontSize = 20.sp
        )


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Nom : AL KHATIB",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            )
            Text(
                text = "Prénom : BASEL",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            )
            Text(
                text = "Adresse mail : basil.kh9957@gmail.com",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            )
            Text(
                text = "Téléphone : 06593077687",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            button(navController, contenu = "submit", viewModel)
        }
    }

}












