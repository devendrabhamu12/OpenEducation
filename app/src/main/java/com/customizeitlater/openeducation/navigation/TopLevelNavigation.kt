package com.customizeitlater.openeducation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.customizeitlater.openeducation.ui.screens.Login.LoginScreen
import com.customizeitlater.openeducation.ui.screens.Register.RegisterScreen
import com.customizeitlater.openeducation.ui.screens.Splash.SplashScreen


@Composable
fun TopLevelNavigation(navController: NavHostController) {
    NavHost(navController, Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(
                onTimeOut =  {navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Splash.route)
                }},
                onFailedJwt ={ navController.navigate(Screen.Login.route){
                    popUpTo(Screen.Splash.route)
                }},
                onSuccessfulJwtRefresh = {navController.navigate(Screen.Home.route){

                    popUpTo(Screen.Splash.route)

                }},
                onFirstLogin={navController.navigate(Screen.Register.route){
                    popUpTo(Screen.Splash.route)
                }}
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Screen.Login.route)
                },
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSucess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(0) { inclusive = true } // Removes the entire backstack
                    }
                }
            )
        }


        composable(Screen.Home.route) {
          HomeNavigation()
        }
    }
}



sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("Home")
}

