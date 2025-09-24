package com.app.imagerandom.presentation.view.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.imagerandom.R


const val SETTING_ROUTE = "setting_route"
fun NavController.navigateToHome() = navigate(SETTING_ROUTE) {
    popUpTo(0) { inclusive = true }
}

fun NavGraphBuilder.settingScreen(
) {
    composable(
        route = SETTING_ROUTE,
    ) {
        SettingScreen()
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SettingScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Setting") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = stringResource(id = R.string.setting_title)
            )
        }
    }
}