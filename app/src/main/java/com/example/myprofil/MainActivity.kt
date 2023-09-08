package com.example.myprofil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myprofil.ui.theme.MyProfilTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyProfilTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                }
                val windowSizeClass = calculateWindowSizeClass(this)
                Screen(windowSizeClass)


            }
        }

    }

    @Composable
    fun Screen(windowClass: WindowSizeClass){
        when (windowClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()) {
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

                    Text(textAlign = TextAlign.Justify, text = "Etudiant en 4ième an alternance "
                        ,
                        fontSize=20.sp)
                    Text(textAlign = TextAlign.Justify, text =
                    "à l'école d'ingénieur ISIS",
                        fontSize=20.sp)


                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()) {
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
                        Button(onClick = {
                            //your onclick code here
                        }) {
                            Text(text = "Démarrer",
                                style = TextStyle(fontSize = 16.sp,
                                )
                            )
                        }
                    }
                }
            }
            else -> {
                Row(Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically) {

                    Column(
                        modifier = Modifier.fillMaxSize()
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
                            text = "Etudiant en 4ième an alternance ",
                            fontSize = 20.sp
                        )
                        Text(
                            textAlign = TextAlign.Justify, text =
                            "à l'école d'ingénieur ISIS",
                            fontSize = 20.sp
                        )
                    }


                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()) {
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
                            Button(onClick = {
                                //your onclick code here
                            }) {
                                Text(text = "Démarrer",
                                    style = TextStyle(fontSize = 16.sp,
                                    )
                                )
                            }
                        }



                }
            }
        }



    }
}



