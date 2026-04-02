package com.ihsanfaiz0048.assesment1_mobpro.navigation

sealed class Screen (val route: String) {
    data object BangunDatar: Screen("bangunDatar")
    data object BangunRuang: Screen("bangunRuang")
    data object Settings: Screen("settingsScreen")
}