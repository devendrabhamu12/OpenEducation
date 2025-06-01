package com.customizeitlater.openeducation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.customizeitlater.openeducation.ui.screens.Login.LoginScreen
import com.customizeitlater.openeducation.ui.screens.Register.RegisterScreen
import com.customizeitlater.openeducation.ui.screens.Splash.SplashScreen


@Composable
fun TopLevelNavigation(navController: NavHostController){
    NavHost(navController, "SplashScreen"  ){

       composable ("SplashScreen"){
           SplashScreen({navController.navigate("RegisterScreen"){popUpTo ("SplashScreen")}})
            }

        composable ("RegisterScreen"){
            RegisterScreen(onRegisterSuccess ={navController.navigate("LoginScreen"){
                popUpTo("LoginScreen")
            } }, onNavigateToLogin = {navController.navigate("LoginScreen") })

        }

        composable ("LoginScreen"){
            LoginScreen(onLoginSucess = {navController.navigate("Home")})
        }
        composable("Home"){
           HomeNavigation()
        }
        }

    }



