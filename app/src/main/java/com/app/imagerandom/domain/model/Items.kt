package com.app.imagerandom.domain.model

data class Items(
    val items: List<BottomTab> = listOf(
        BottomTab.Home,
        BottomTab.EqSetting,
        BottomTab.Setting,
    )
)
