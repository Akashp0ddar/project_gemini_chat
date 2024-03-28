package com.example.gemini

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.invoke.MethodHandles.Lookup

class MainViewModel : ViewModel() {
    private val _response = MutableStateFlow("")
    val response: StateFlow<String> = _response

    var prompt = mutableStateOf("")


    private val generativeModel =
        GenerativeModel(modelName = "gemini-pro", apiKey = BuildConfig.api_key)

    fun generatePrompt(prompt:String){
        viewModelScope.launch(Dispatchers.IO) {
            _response.value = generativeModel.generateContent(prompt).text.toString()
        }
    }
}