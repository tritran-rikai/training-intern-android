package com.app.imagerandom.domain.model

import com.app.imagerandom.R
import com.app.imagerandom.presentation.view.home.HOME_ROUTE
import com.app.imagerandom.presentation.view.like.LIKE_ROUTE
import com.app.imagerandom.presentation.view.setting.SETTING_ROUTE

sealed class BottomTab(
    val route: String,
    val icon: Int,
    val label: String,
    var isLoaded: Boolean = false,
) {
    data object Home : BottomTab(HOME_ROUTE, R.drawable.home_svg, "Home")
    data object EqSetting : BottomTab(LIKE_ROUTE, R.drawable.eq_svg, "EQ")
    data object Setting : BottomTab(SETTING_ROUTE, R.drawable.setting_svg, "Setting")

    companion object {
        val items = listOf(
            Home,
            EqSetting,
            Setting,
        )

        fun fromRoute(route: String): BottomTab =
            Items().items.firstOrNull { it.route == route } ?: Home
    }
}