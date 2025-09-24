package com.app.imagerandom.presentation.view.like

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val LIKE_ROUTE = "like_route"
fun NavController.navigateToLike() = navigate(LIKE_ROUTE) {
    popUpTo(0) { inclusive = true }
}

fun NavGraphBuilder.likeScreen(
) {
    composable(
        route = LIKE_ROUTE,
    ) {
        LikeScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Like") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Welcome to the Like Screen!")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* Handle button click */ }) {
                Text("Click Me")
            }
        }
    }
}