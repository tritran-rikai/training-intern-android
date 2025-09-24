package com.app.imagerandom.presentation.view.home

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.imagerandom.domain.model.ImageRandom
import com.app.imagerandom.presentation.viewmodel.theme.HomeViewModel


const val HOME_ROUTE = "home_route"
fun NavController.navigateToHome() = navigate(HOME_ROUTE) {
    popUpTo(0) { inclusive = true }
}

fun NavGraphBuilder.homeScreen(
) {
    composable(
        route = HOME_ROUTE,
    ) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val list by viewModel.listImageState.collectAsState()
        HomeScreen(list, viewModel::getListImageRandom)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(list: List<ImageRandom>, function: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Home") })
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
            Text("Welcome to the Home Screen!")
            Spacer(modifier = Modifier.height(16.dp))
            Text("List of images:")
            Spacer(modifier = Modifier.height(8.dp))
            list.forEach { image ->
                Text(image.url)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = function) {
                Text("Get Random Image")
            }
        }
    }
}