package com.app.imagerandom.presentation.view.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.imagerandom.data.local.SharedPrefHelper
import com.app.imagerandom.presentation.navigation.Screen
import com.app.imagerandom.presentation.viewmodel.AuthViewModel

fun NavController.navigateToCreateSession() {
    navigate(Screen.CREATE_SESSION) {
        popUpTo(graph.startDestinationId) { inclusive = true }
        launchSingleTop = true
    }
}
fun NavGraphBuilder.createSessionScreen() {
    composable(route = Screen.CREATE_SESSION) {
        val context = LocalContext.current
        val prefs = remember { SharedPrefHelper(context) }
        val viewModel = hiltViewModel<AuthViewModel>()
        val token by viewModel.token.observeAsState()
        val loading by viewModel.loading.observeAsState(false)
        val error by viewModel.error.observeAsState()
        val isValidRequestToken by viewModel.isValidRequestToken.observeAsState()
        val sessionId by viewModel.sessionId.observeAsState()

        LaunchedEffect(Unit) {
            viewModel.fetchToken()
        }

        LaunchedEffect(token) {
            token?.let { token ->
                viewModel.validateRequestToken(token)
            }
        }

        LaunchedEffect(isValidRequestToken) {
            if (isValidRequestToken == true && token != null) {
                viewModel.createSession(token!!)
            }
        }

        LaunchedEffect(sessionId) {
            if (sessionId != null) {
                prefs.saveSessionId(sessionId!!)
            }
        }
        CreateSessionScreen(loading, error)
    }
}

@Composable
fun CreateSessionScreen(
    loading: Boolean,
    error: String?
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            loading -> {
                CircularProgressIndicator()
            }

            error != null -> {
                Text(text = "Error: $error")
            }
        }
    }
}