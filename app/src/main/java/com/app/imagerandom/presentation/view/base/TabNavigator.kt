package com.app.imagerandom.presentation.view.base

import android.util.Log
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import com.app.imagerandom.domain.model.BottomTab

class TabNavigator {
    private val _navControllers = mutableMapOf<BottomTab, NavHostController>()
    private var setTabCallback: ((BottomTab) -> Unit)? = null

    fun register(
        controllers: Map<BottomTab, NavHostController>,
        onTabSelected: (BottomTab) -> Unit
    ) {
        _navControllers.clear()
        _navControllers.putAll(controllers)
        setTabCallback = onTabSelected
    }

    fun navigateTo(tab: BottomTab, route: String) {
        Log.d("TabNavigator", "Navigating to ${tab.route} -> $route")
        setTabCallback?.invoke(tab)
        _navControllers[tab]?.navigate(route)
        Log.d("TabNavigator", "_navControllers $_navControllers _navControllers[tab] ${_navControllers[tab]}")
    }

    fun getNavController(tab: BottomTab): NavHostController? = _navControllers[tab]
}

val LocalTabNavigator = staticCompositionLocalOf<TabNavigator> {
    error("No TabNavigator provided")
}