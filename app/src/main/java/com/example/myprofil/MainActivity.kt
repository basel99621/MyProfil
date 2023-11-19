package com.example.myprofil

import FilmsDetails
import MainViewModel
import Screen
import affichageFilmsSemaine
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myprofil.ui.theme.MyProfilTheme
import com.example.premiereapp.SerieDetails




class MainActivity : ComponentActivity() {

    private val viewModel:MainViewModel by viewModels()
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val filmId = viewModel.filmId
            val serieId = viewModel.serieId
            val navController = rememberNavController()
            MyProfilTheme {
                //Navigation(navController = navController,windowSizeClass)
            }

            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background

            ) {



                NavHost(
                    navController = navController,
                    startDestination = "profil",

                    ) {
                    composable("profil") {
                        Screen(windowSizeClass, navController, viewModel)
                    }
                    composable("films") {
                        Screenfilm(serieId,filmId,navController , viewModel =viewModel ,windowSizeClass)
                    }
                    composable(route = "filmDetails"){
                        FilmsDetails(viewModel=viewModel, filmId.collectAsState().value, windowSizeClass)

                    }
                    composable("affichageFilms") {
                        affichageFilmsSemaine(filmId, navController, viewModel = viewModel)
                    }
                    composable("affichageSéries") {
                        SerieDetails(viewModel = viewModel, serieId.collectAsState().value )


                    }

                }


            }

        }

        /*  @Composable
    fun Navigation(
        navController: NavHostController,
        windowSizeClass: WindowSizeClass,

    ) {


        NavHost(
            navController = navController,
            startDestination = "profil"
        ) {
            composable(
                route="profil"
            ){
                Screen(windowSizeClass,navController)
            }
            composable(
                route="films"
            ){
                Screenfilm(windowClass = windowSizeClass)


            }
            composable(route = "filmDetails"){
                //FilmsDetails(viewModel(), "movie/{id}")*
                Text(text = "La navigation fonctionne")
            }

        }

    }*/
    }
}








