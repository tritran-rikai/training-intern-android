package com.app.imagerandom.domain.usecase.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.imagerandom.data.local.SharedPrefHelper
import com.app.imagerandom.presentation.navigation.Screen
import com.app.imagerandom.presentation.view.auth.SignInScreen
import com.app.imagerandom.presentation.view.auth.navigateToSignIn
import com.app.imagerandom.presentation.view.auth.navigateToSignUp
import com.app.imagerandom.presentation.viewmodel.AuthViewModel

fun NavController.navigateToHome() {
    navigate(Screen.HOME) {
        popUpTo(graph.startDestinationId) { inclusive = true }
        launchSingleTop = true
    }
}

fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable(
        route = Screen.HOME,
    ) {

        HomeScreen(
            navigateToSignIn = {
                navController.navigateToSignIn("", "")
            }
        )
    }
}

@Composable
fun HomeScreen(
    navigateToSignIn: () -> Unit,
) {
    val context = LocalContext.current
    val prefs = remember { SharedPrefHelper(context) }
    val sessionId = prefs.getSessionId()
    if (sessionId.isNullOrEmpty()) {
        navigateToSignIn()
    }
}