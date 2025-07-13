package com.customizeitlater.openeducation.ui.screens.aphorismcreate//package com.customizeitlater.openeducation.ui.screens.aphorismcreate


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.customizeitlater.openeducation.data.network.AphorismApi
import com.customizeitlater.openeducation.data.network.requestmodel.QuestionDTO
import com.customizeitlater.openeducation.data.network.requestmodel.SaveAphorismRequest
import com.customizeitlater.openeducation.data.network.responsemodel.AphorismDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AphorismCreateViewModel @Inject constructor(
    private val aphorismApi: AphorismApi
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateAphorismUiState())
    val uiState: StateFlow<CreateAphorismUiState> = _uiState.asStateFlow()


    private val _createdAphorism = MutableStateFlow<AphorismCreateResponse?>(null)
    val createdAphorism: StateFlow<AphorismCreateResponse?> = _createdAphorism.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    fun onUiStateChange(newState: CreateAphorismUiState) {
        _uiState.value = newState
    }
///

    ///sage master will always do the thingi so do it other way



    ///
    fun onSubmitClick(sageId: String="master") {
        val current = _uiState.value
        _createdAphorism.value= AphorismCreateResponse.Loading

        // Optional: validate
        if (current.title.isBlank() || current.question.isBlank() || sageId.isBlank()) {
            _message.value = "Please fill all required fields"
            return
        }

        // Convert UI state into backend request
        val request = SaveAphorismRequest(
            title = current.title,
            sageId = sageId,
            question = QuestionDTO(
                question = current.question,
                option1 = current.option1,
                option2 = current.option2,
                option3 = current.option3,
                option4 = current.option4,
                answer = current.correctAnswer,
                explanation = current.explanation
            ),
            tagNames = current.tags
                .split(",")                      // comma separated
                .map { it.trim().lowercase() }   // clean
                .filter { it.isNotBlank() }      // remove blanks
                .toSet()
        )

        // Launch coroutine
        viewModelScope.launch {
            _loading.value = true
            _message.value = null
            _createdAphorism.value=null

            try {
                val response = aphorismApi.createAphorism(request)
                if (response.isSuccessful && response.body()?.success == true) {
                    _message.value = "Aphorism created successfully!"
                    // optionally clear form:
                    _createdAphorism.value= AphorismCreateResponse.Loaded(response.body()?.items?.first())

                    _uiState.value = CreateAphorismUiState()
                } else {
                    _message.value = response.body()?.message ?: "Submission failed"
                }
            } catch (e: Exception) {
                _message.value = "Error: ${e.localizedMessage ?: "Unknown error"}"
            } finally {
                _loading.value = false
            }
        }
    }
}


sealed class AphorismCreateResponse{
    object Loading: AphorismCreateResponse()
    class Loaded(val aphorismResponse: AphorismDTO?): AphorismCreateResponse()
}
