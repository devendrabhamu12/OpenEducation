package com.customizeitlater.openeducation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.customizeitlater.openeducation.ui.screens.Splash.SplashScreen


@Composable
fun TopLevelNavigation(navController: NavHostController){
    NavHost(navController, "SplashScreen"  ){

       composable ("SplashScreen"){
           SplashScreen({navController.navigate("LoginScreen"){popUpTo ("SplashScreen")}})
            }


        composable ("LoginScreen"){
            Box{Text("hello login here")}
        }


    }}


