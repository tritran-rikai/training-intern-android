package com.app.imagerandom.presentation.view

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.app.imagerandom.domain.model.BottomTab
import com.app.imagerandom.presentation.view.home.homeScreen
import com.app.imagerandom.presentation.view.imagedetail.navigation.imageDetailScreen
import com.app.imagerandom.presentation.view.like.likeScreen
import com.app.imagerandom.presentation.view.setting.settingScreen

@Composable
fun TabContentHost(
    tab: BottomTab,
    navController: NavHostController,
) {
    LaunchedEffect(Unit) {
        navController.addOnDestinationChangedListener(listener = { controller, destination, arguments ->
            Log.d("TAG", "TabContentHost: ${destination.route}")
        })
    }
    NavHost(navController, startDestination = tab.route) {
        when (tab) {
            is BottomTab.Home -> {
                homeScreen(navController)
                imageDetailScreen(navController)
            }

            is BottomTab.EqSetting -> {
                likeScreen()
            }

            is BottomTab.Setting -> {
                settingScreen()
            }
        }
    }
}