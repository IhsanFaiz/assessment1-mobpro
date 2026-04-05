package com.ihsanfaiz0048.assesment1_mobpro.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ihsanfaiz0048.assesment1_mobpro.ui.screen.BangunDatarScreen
import com.ihsanfaiz0048.assesment1_mobpro.ui.screen.BangunRuangScreen
import com.ihsanfaiz0048.assesment1_mobpro.ui.screen.contentBangunDatar.SettingsContent

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController(), modifier: Modifier, isDarkMode: Boolean, onToggleDarkMode: () -> Unit){
    NavHost(
        navController = navController,
        startDestination = Screen.BangunDatar.route,
        modifier = modifier
    ) {
        composable(Screen.BangunDatar.route) { BangunDatarScreen() }
        composable(Screen.BangunRuang.route) { BangunRuangScreen() }
        composable(Screen.Settings.route) {
            SettingsContent(
                isDarkMode = isDarkMode,
                onToggleDarkMode = onToggleDarkMode
            )
        }
    }
}