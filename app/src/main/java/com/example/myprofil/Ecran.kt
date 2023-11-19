package com.example.myprofil

import Film
import MainViewModel
import RechercheActeurs
import RechercheFilms
import RechercheSeries
import affichageActeursSemaine
import affichageSeriesSemaine
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Screenfilm(
    serieId: MutableStateFlow<String>,
    filmId: MutableStateFlow<String>,
    navController: NavController,
    viewModel: MainViewModel,
    windowClass: WindowSizeClass
) {

        when (windowClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                ScaffoldExample(serieId, filmId, navController, viewModel, windowClass)
            }
            else -> {
                ScaffoldExample(serieId, filmId, navController, viewModel, windowClass)
            }
        }

}




@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample(serieId: MutableStateFlow<String>, filmId:MutableStateFlow<String>, navController: NavController, viewModel: MainViewModel, windowClass: WindowSizeClass) {
    var  selectedItem by remember { mutableStateOf("") }

    var showSearchBar by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {

            Scaffold(

                topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = MaterialTheme.colors.primary
                            ),

                            title = {
                                if (!showSearchBar) {
                                    Text(
                                        text = "FILM APP",
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }
                            ,
                            actions = {

                                if (showSearchBar) {
                                    TextField(
                                        value = searchText, // Valeur du champ de texte
                                        onValueChange = {  searchText = it },
                                        placeholder = { Text("Rechercher par mot clé" ,
                                            color = Color.White) },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(5.dp)

                                            .clip(CircleShape)  // Make it oval-shaped
                                            .border(1.dp, Color.Gray, CircleShape)
                                    )
                                }

                               if(viewModel.iconeVisible.value) {
                                   Icon(
                                       modifier = Modifier
                                           .clickable {
                                               showSearchBar = true;
                                               viewModel.iconeVisible.value = false;
                                           }
                                           .size(40.dp),
                                       imageVector = Icons.Default.Search,
                                       contentDescription = "Search",
                                       tint = Color.White
                                   )
                               }
                                viewModel.iconeVisible.value = true

                            },

                        )




                    // Affichage du champ de texte lorsqu'on clique sur l'icône de recherche



                },

                bottomBar = {
                    BottomNavigation {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination

                        BottomNavigationItem(
                            icon = { Icon(painter = painterResource(id = R.drawable.baseline_movie_24),
                                contentDescription = "icone du film")},
                            label = { Text("Films",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,

                                    color = Color.White
                                )
                            )
                            },
                            selected = currentDestination?.hierarchy?.any { it.route == "films" } == true,
                            onClick = {
                                selectedItem="Films"
                                searchText=""
                                showSearchBar=false

                            }
                        )
                        BottomNavigationItem(
                            icon = { Icon(painter = painterResource(id = R.drawable.baseline_tv_24),
                                contentDescription = "icone du film") },
                            label = { Text("Séries",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,

                                    color = Color.White

                                )
                            )
                            },
                            selected = currentDestination?.hierarchy?.any { it.route == "series" } == true,
                            onClick = {

                                selectedItem="Séries"
                                searchText=""
                                showSearchBar=false
                            }
                        )
                        BottomNavigationItem(
                            icon = { Icon(painter = painterResource(id = R.drawable.baseline_person_24),
                                contentDescription = "icone du film") },
                            label = { Text("Acteurs",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            )
                            },
                            selected = currentDestination?.hierarchy?.any { it.route == "acteurs" } == true,
                            onClick = {
                                selectedItem = "Acteurs"
                                showSearchBar=false
                                searchText=""
                            }
                        )

                    }
                }
            ) {
                    innerPadding -> Box(modifier  = Modifier.padding(innerPadding)) {

                when (selectedItem) {

                    "Films" -> if(showSearchBar){

                        if (searchText.isEmpty()){
                            Film(filmId ,navController ,viewModel = MainViewModel())
                        } else if(searchText!="") {
                            RechercheFilms(filmId, navController ,name =searchText, viewModel = MainViewModel())
                        }
                    } else {
                        Film(filmId ,navController ,viewModel = MainViewModel())
                    }


                    "Séries" -> if (showSearchBar){

                        if (searchText.isEmpty()){
                            affichageSeriesSemaine(serieId, navController,viewModel = MainViewModel())
                        }
                        else {
                            RechercheSeries(serieId, navController,name = searchText, viewModel = MainViewModel())
                        }
                    } else {
                        affichageSeriesSemaine(serieId, navController,viewModel = MainViewModel())
                    }

                    "Acteurs" -> if (showSearchBar){


                        if (searchText.isEmpty()){
                            affichageActeursSemaine(viewModel = MainViewModel(), windowClass)
                        }
                        else {
                            RechercheActeurs(name = searchText, viewModel = MainViewModel())
                        }
                    } else {
                        affichageActeursSemaine(viewModel = MainViewModel(), windowClass)
                    }


                    else -> if(viewModel.rememberSelectedItemPortrait.value=="" && searchText=="") {
                        Film(filmId ,navController ,viewModel = MainViewModel())
                    } else if (viewModel.rememberSelectedItemPortrait.value!="") {
                        selectedItem=viewModel.rememberSelectedItemPortrait.value
                    } else if(searchText!="") { RechercheFilms(filmId , navController , name = searchText, viewModel = MainViewModel() )}

                }
                viewModel.rememberSelectedItem.value=selectedItem
                viewModel.rememberSelectedItemPortrait.value=selectedItem
                viewModel.rememberDernierRecherche.value=searchText
            }
            }

        } else -> {
        RailBar(serieId ,navController ,filmId ,viewModel, windowClass)

        }

    }


}

@SuppressLint("RememberReturnType", "StateFlowValueCalledInComposition", "UnrememberedMutableState")
@Composable
fun RailBar(serieId:MutableStateFlow<String>,navController: NavController ,filmId:MutableStateFlow<String>  ,viewModel: MainViewModel, windowClass: WindowSizeClass){
viewModel.iconeVisible.value=true
    val navControllerBis = rememberNavController()
    var showSearchBar by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var  selectedItem by remember { mutableStateOf("") }
    val navBackStackEntry by navControllerBis.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    NavigationRail (
       containerColor = MaterialTheme.colors.primary,
        modifier = Modifier.background(color = Color.White)
    ){
        Row (){
            Column(verticalArrangement=Arrangement.SpaceBetween
                ) {
                NavigationRailItem(
                    icon = { Icon(painter = painterResource(id = R.drawable.baseline_movie_24),
                        contentDescription = "icone du film",
                        tint = Color.White) },
                    label = { Text("Films",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,

                            color = Color.White

                        )
                    )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == "films" } == true,
                    onClick = {
                        selectedItem="Films"
                        searchText=""
                        showSearchBar=false
                    }
                )
            Spacer(modifier = Modifier.size(80.dp))
                NavigationRailItem(
                    icon = { Icon(painter = painterResource(id = R.drawable.baseline_tv_24),
                        contentDescription = "icone du tv",
                        tint = Color.White) },
                    label = { Text("Séries",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,

                            color = Color.White

                        )
                    )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == "series" } == true,
                    onClick = {
                        selectedItem="Séries"
                        searchText=""
                        showSearchBar=false

                    }
                )
                Spacer(modifier = Modifier.size(80.dp))
                NavigationRailItem(
                    icon = { Icon(painter = painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = "icone d'une personne",
                        tint = Color.White) },
                    label = { Text("Acteurs",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == "acteurs" } == true,
                    onClick = {
                        selectedItem="Acteurs"
                        searchText=""
                        showSearchBar=false


                    }
                )
            }

Box (
    Modifier
        .background(Color.White)
        .fillMaxSize()
) {
    when (selectedItem ) {


        "Films" -> if (showSearchBar) {

            if (searchText.isEmpty()) {
                Film(filmId,navController , viewModel = MainViewModel())
            } else if(searchText!="") {

                RechercheFilms(filmId, navController ,name = searchText, viewModel = MainViewModel())
            } else if(viewModel.rememberDernierRecherche.value!=""){
                RechercheFilms(filmId, navController ,name = viewModel.rememberDernierRecherche.value, viewModel = MainViewModel())
            }
        } else {
            Film(filmId ,navController , viewModel = MainViewModel())
        }


        "Séries" -> if (showSearchBar) {


            if (searchText.isEmpty()) {
                affichageSeriesSemaine(serieId, navController,viewModel = MainViewModel())
            } else {
                RechercheSeries(serieId,navController ,name = searchText, viewModel = MainViewModel())
            }
        } else {
            affichageSeriesSemaine(serieId, navController,viewModel = MainViewModel())
        }

        "Acteurs" -> if (showSearchBar) {
            if (searchText.isEmpty()) {
                affichageActeursSemaine(viewModel = MainViewModel(), windowClass)
            } else {
                RechercheActeurs(name = searchText, viewModel = MainViewModel())
            }
        } else {
            affichageActeursSemaine(viewModel = MainViewModel(), windowClass)
        }


        else -> if(viewModel.rememberSelectedItem.value=="") {
            Film(filmId ,navController ,viewModel = MainViewModel())
        } else if(viewModel.rememberSelectedItem.value!="") {
            selectedItem = viewModel.rememberSelectedItem.value
        }

    }

    viewModel.rememberSelectedItemPortrait.value=selectedItem
    viewModel.rememberSelectedItem.value=selectedItem


    Row(modifier= Modifier
        .padding(end = 16.dp, bottom = 16.dp)
        .align(Alignment.BottomEnd)) {

        if (showSearchBar) {
            TextField(
                value = searchText, // Valeur du champ de texte
                onValueChange = {  searchText = it },
                placeholder = { Text("Rechercher par mot clé") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 1.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)

            )
        }

        if(viewModel.iconeVisible.value) {
            Icon(
                modifier = Modifier
                    .clickable {
                        showSearchBar = true;
                        viewModel.iconeVisible.value = false;
                    }
                    .size(40.dp),
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray
            )
        }





    }
    viewModel.iconeVisible.value = true









}




        }







    }


}











