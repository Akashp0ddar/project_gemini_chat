package com.example.gemini

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gemini.ui.theme.GeminiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeminiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel by viewModels<MainViewModel>()
                    ChatScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun ChatScreen(viewModel: MainViewModel) {
    val response by viewModel.response.collectAsState()

    val scroll = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(enabled = true, state = scroll)
        ) {

            // Text at the top
            Text(
                text = response,
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 10.dp)
            )

            // Spacer to push text to the top and the following elements to the bottom
            Spacer(modifier = Modifier.weight(1f))

            // TextField
            OutlinedTextField(
                value = viewModel.prompt.value,
                onValueChange = { viewModel.prompt.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Button
            Button(
                onClick = {
                    viewModel.generatePrompt(prompt = viewModel.prompt.value)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp).padding(horizontal = 10.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Submit prompt")
            }
        }
    }
}


