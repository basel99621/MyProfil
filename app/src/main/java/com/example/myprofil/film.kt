package com.example.myprofil

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun Screenfilm(windowClass: WindowSizeClass) {

    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Text(text = "Hello")
            ScaffoldExample()
        }
        else ->{
            Text(text = "Vous êtes en paysage")
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {},

        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination



                    BottomNavigationItem(
                        icon = { Icon(painter = painterResource(id = R.drawable.baseline_movie_24),
                            contentDescription = "icone du film") },
                        label = { Text("Films",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,

                                color = Color.White
                            )
                        )
                                },
                        selected = currentDestination?.hierarchy?.any { it.route == "films" } == true,
                        onClick = {

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
                    selected = currentDestination?.hierarchy?.any { it.route == "films" } == true,
                    onClick = {

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
                    selected = currentDestination?.hierarchy?.any { it.route == "films" } == true,
                    onClick = {

                    }
                )

            }
        }
    ) {
            innerPadding -> Box(modifier  = Modifier.padding(innerPadding)) {
        Text("coucou")


    }
    }
}

