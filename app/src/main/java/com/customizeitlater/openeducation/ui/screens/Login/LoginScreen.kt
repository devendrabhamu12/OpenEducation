package com.customizeitlater.openeducation.ui.screens.Login


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginSucess:()-> Unit,

    viewModel: LoginViewModel= hiltViewModel<LoginViewModel>()
) {
    val loginUserDetails by viewModel.loginUser.collectAsState()
    val loginState by viewModel.loginState.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }
    val focusManager= LocalFocusManager.current



    when (loginState) {
        is LoginState.Loading -> {
            // Show CircularProgressIndicator()
            Text("Loading")
            Log.d("Login Helper","loading app for login")
            Log.d("Login Helper",loginUserDetails.identity)
        }
        is LoginState.Error -> {
            Text(text = (loginState as LoginState.Error).error)
            Log.d("Login Helper",loginState.toString())
            Log.d("Login Helper",loginUserDetails.identity)


        }
        else -> LaunchedEffect (loginState){
            if(loginState is LoginState.Success)
                onLoginSucess()
            Log.d("Login Helper",loginState.toString())

        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome Back 👋",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Login to continue",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            OutlinedTextField(
                value = loginUserDetails.identity,
                onValueChange = {viewModel.updateLoginDetails(name = it) },
                label = { Text("Email,identity or phone number") },
                singleLine = true,
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            OutlinedTextField(
                value = loginUserDetails.password,
                onValueChange = {viewModel.updateLoginDetails(password=it) },
                label = { Text("Password") },
                singleLine = true,
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            },
                            contentDescription = "Toggle Password Visibility"
                        )
                    }

                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                visualTransformation = if (passwordVisible) {
                    VisualTransformation.None
                } else{ PasswordVisualTransformation()}
            )



            Button(
                onClick = {
                    viewModel.loginUser()
                    },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(16.dp)),
                colors = ButtonColors(MaterialTheme.colorScheme.secondaryContainer,
                    MaterialTheme.colorScheme.onSecondaryContainer,
                    MaterialTheme .colorScheme.tertiaryContainer,
                    MaterialTheme.colorScheme.onTertiaryContainer)

            ) {
                Text("Login", style = MaterialTheme.typography.titleMedium)
            }

            TextButton(onClick = { /* TODO: Navigate to Forgot Password */ }) {
                Text("Forgot Password?", color = MaterialTheme.colorScheme.primary)
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = { /* TODO: Navigate to Sign Up */ }) {
                Text("Don’t have an account? Sign up")
            }
        }
    }
}

