package com.app.imagerandom.presentation.view.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.imagerandom.presentation.navigation.Screen
import com.app.imagerandom.presentation.view.auth.navigateToSignIn
import com.app.imagerandom.presentation.viewmodel.HomeViewModel

fun NavController.navigateToHome() {
    navigate(Screen.HOME)
}

fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable(
        route = Screen.HOME,
    ) {
        val viewModel = hiltViewModel<HomeViewModel>()
        HomeScreen(
            navController = navController,
            isAutoSignIn = viewModel.checkAutoSignIn()
        )
    }
}

@Composable
fun HomeScreen(
    navController: NavController,
    isAutoSignIn: Boolean
) {
    if (!isAutoSignIn) {
        navController.navigateToSignIn("", "")
    }
}