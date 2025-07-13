package com.customizeitlater.openeducation.ui.screens.Splash

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.customizeitlater.openeducation.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(onTimeOut:()-> Unit,
                 onSuccessfulJwtRefresh:()-> Unit,
                 onFailedJwt:()-> Unit,
                 onFirstLogin:()-> Unit,
              viewModel: SplashViewModel= hiltViewModel()
) {
    var progress by remember { mutableFloatStateOf(0f) }
    val splashState =viewModel.splashState

    LaunchedEffect(splashState,progress) {
        if ( progress >= 1f) {
            when (splashState.value) {
               SplashState.FirstLogin -> {onFirstLogin()
                    Log.d("state firstLogin" ,splashState.value.toString())
                }
               SplashState.JwtRefreshed ->{
                    onSuccessfulJwtRefresh()
                    Log.d("state jwtrefreshed " ,splashState.value.toString())

                }
               SplashState.JwtFailed ->{onFailedJwt()
                    Log.d("state jwt failed" ,splashState.value.toString())
                   Log.d("which","jwt failed")

                }
                else -> {
                    Log.d("which","jwt time out")
                            onTimeOut()
                }
            }
        }
    }
//    LaunchedEffect(progress) {
//        if (progress >= 1f && splashState == SplashState.Loading) {
//            // Only timeout if it's still stuck in loading
//            onTimeOut()
//        }
//    }



    // Simulate progress increment
    LaunchedEffect(Unit) {
        while (progress < 1f) {
            delay(20)
            progress += 0.002f
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(24.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(120.dp)
            )
            Text("Education!!How??", color = MaterialTheme.colorScheme.onErrorContainer)

            Spacer(modifier = Modifier.height(32.dp))

            LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.onSecondary,
            strokeCap = StrokeCap.Butt,
            )
        }
    }
}


