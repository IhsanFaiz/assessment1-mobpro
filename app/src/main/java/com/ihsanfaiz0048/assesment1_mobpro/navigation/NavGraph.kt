package com.ihsanfaiz0048.assesment1_mobpro.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ihsanfaiz0048.assesment1_mobpro.ui.screen.BangunDatarScreen
import com.ihsanfaiz0048.assesment1_mobpro.ui.screen.BangunRuangScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController(), modifier: Modifier){
    NavHost(
        navController = navController,
        startDestination = Screen.BangunDatar.route,
        modifier = modifier
    ) {
        composable(Screen.BangunDatar.route) { BangunDatarScreen() }
        composable(Screen.BangunRuang.route) { BangunRuangScreen() }
    }
}