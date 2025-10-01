package com.app.imagerandom.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.imagerandom.presentation.view.auth.CreateSessionScreen
import com.app.imagerandom.presentation.view.auth.SignInScreen
import com.app.imagerandom.presentation.view.auth.SignUpScreen
import com.app.imagerandom.presentation.view.auth.createSessionScreen
import com.app.imagerandom.presentation.view.auth.navigateToCreateSession
import com.app.imagerandom.presentation.view.auth.signInScreen
import com.app.imagerandom.presentation.view.auth.signUpScreen

object Screen {
    const val SIGN_IN = "sign_in"
    const val SIGN_UP = "sign_up"
    const val CREATE_SESSION = "create_session"
}


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SIGN_IN
    ) {
        signUpScreen(navController)
        signInScreen(navController)
        createSessionScreen()
    }
}