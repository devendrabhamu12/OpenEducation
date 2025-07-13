package com.customizeitlater.openeducation.ui.screens.aphorismcreate

import android.widget.Toast


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CreateAphorismScreen(
    viewModel: AphorismCreateViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val isAphorismCreated by viewModel.createdAphorism.collectAsState()
val context = LocalContext.current



//    LaunchedEffect(isAphorismCreated) {
//        val loaded = isAphorismCreated as? AphorismCreateResponse.Loaded
//        loaded?.aphorismResponse?.let { dto ->
//         //Logic for telling User that the Aphorism is Created
//            Toast.makeText(
//                context,
//                "Aphorism Created and ID is ${(isAphorismCreated as AphorismCreateResponse.Loaded).aphorismResponse?.postId}",
//                Toast.LENGTH_LONG
//            ).show()
//
//
//        }
//    }

    when(isAphorismCreated){
        is AphorismCreateResponse.Loaded->{

        }
        else -> {
            Toast.makeText(
                context,
                "Aphorism Created and ID is ${(isAphorismCreated as AphorismCreateResponse.Loaded).aphorismResponse?.postId}",
                Toast.LENGTH_LONG
            ).show()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        InputField(
            label = "Title",
            value = uiState.title,
            onValueChange = { viewModel.onUiStateChange(uiState.copy(title = it)) }
        )

        InputField(
            label = "Question",
            value = uiState.question,
            onValueChange = { viewModel.onUiStateChange(uiState.copy(question = it)) }
        )

        InputField(
            label = "Option 1",
            value = uiState.option1,
            onValueChange = { viewModel.onUiStateChange(uiState.copy(option1 = it)) }
        )
        InputField(
            label = "Option 2",
            value = uiState.option2,
            onValueChange = { viewModel.onUiStateChange(uiState.copy(option2 = it)) }
        )
        InputField(
            label = "Option 3",
            value = uiState.option3,
            onValueChange = { viewModel.onUiStateChange(uiState.copy(option3 = it)) }
        )
        InputField(
            label = "Option 4",
            value = uiState.option4,
            onValueChange = { viewModel.onUiStateChange(uiState.copy(option4 = it)) }
        )

        Text("Correct Answer", style = MaterialTheme.typography.titleMedium)
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            (1..4).forEach { index ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    RadioButton(
                        selected = uiState.correctAnswer == index,
                        onClick = { viewModel.onUiStateChange(uiState.copy(correctAnswer = index)) }
                    )
                    Text(text = "Option $index")
                }
            }
        }

        InputField(
            label = "Explanation (optional)",
            value = uiState.explanation,
            onValueChange = { viewModel.onUiStateChange(uiState.copy(explanation = it)) }
        )

        InputField(
            label = "Tags (comma-separated)",
            value = uiState.tags,
            onValueChange = { viewModel.onUiStateChange(uiState.copy(tags = it)) }
        )

        Button(
            onClick = { viewModel.onSubmitClick() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Submit Aphorism")
        }
    }
}

@Composable
private fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}
