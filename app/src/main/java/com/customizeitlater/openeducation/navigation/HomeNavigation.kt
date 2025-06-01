package com.customizeitlater.openeducation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeNavigation(){
    val navController= rememberNavController()
    val currentDestination by navController.currentBackStackEntryAsState()
    val route = currentDestination?.destination?.route

    Scaffold(
        topBar = {
            when (route) {
//                "home" -> HomeTopBar()
//                "create_tag" -> CreateTagTopBar()
//                else -> DefaultTopBar()
            }
        },
        bottomBar = {
            if (route in listOf("home", "create_tag", "other_screen")) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(navController) }
            composable("create_tag") { CreateTagScreen(navController) }
            // Add other screens here
        }
    }
}

@Composable
fun HomeScreen(x0: NavHostController) {
    TODO("Not yet implemented")
}

@Composable
fun CreateTagScreen(x0: NavHostController) {
    TODO("Not yet implemented")
}

@Composable
fun BottomNavigationBar(x0: NavHostController) {
    TODO("Not yet implemented")
}