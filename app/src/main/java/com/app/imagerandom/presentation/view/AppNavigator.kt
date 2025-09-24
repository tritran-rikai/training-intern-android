package com.app.imagerandom.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.imagerandom.domain.model.BottomTab
import com.app.imagerandom.domain.model.BottomTab.EqSetting
import com.app.imagerandom.domain.model.BottomTab.Home
import com.app.imagerandom.domain.model.BottomTab.Setting
import com.app.imagerandom.domain.model.Items
import com.app.imagerandom.presentation.view.base.LocalTabNavigator
import com.app.imagerandom.presentation.view.base.TabNavigator
import com.app.imagerandom.presentation.view.customview.BottomNavigation


const val APP_NAVIGATION = "app_navigation"
fun NavController.navigateToAppNavigation() = navigate(APP_NAVIGATION) {
    popUpTo(0) { inclusive = true }
}

fun NavGraphBuilder.appNavigation() {
    composable(
        route = APP_NAVIGATION,
    ) {
        AppNavigator()
    }
}

@Composable
fun AppNavigator() {

    val tabs = Items().items
    var selectedTabRoute by rememberSaveable { mutableStateOf(Home.route) }
    val selectedTab = BottomTab.fromRoute(selectedTabRoute)

    val homeNavController = rememberNavController()
    val eqSettingNavController = rememberNavController()
    val settingNavController = rememberNavController()

    val navControllers = mapOf(
        Home to homeNavController,
        EqSetting to eqSettingNavController,
        Setting to settingNavController
    )

    val tabNavigator = remember { TabNavigator() }


    Box(modifier = Modifier.fillMaxSize()) {
        CompositionLocalProvider(LocalTabNavigator provides tabNavigator) {
            Scaffold(
                bottomBar = {
                    BottomNavigation(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .navigationBarsPadding()
                            .zIndex(10f),
                        currentTab = selectedTab,
                        onTabSelected = {
                            selectedTabRoute = it.route
                        },
                    )
                }
            ) { paddingValues ->
                Box {
                    tabs.forEach { tab ->
                        val navController = navControllers[tab]!!
                        val isVisible = tab.route == selectedTabRoute
                        if (isVisible)
                            Box(
                                modifier = Modifier
                                    .padding(paddingValues)
                                    .fillMaxSize()
                            ) {
                                TabContentHost(
                                    tab = tab,
                                    navController = navController,
                                )
                            }
                    }
                }
            }
        }
    }
}