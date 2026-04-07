package com.ihsanfaiz0048.assesment1_mobpro.ui.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ihsanfaiz0048.assesment1_mobpro.R
import com.ihsanfaiz0048.assesment1_mobpro.navigation.Screen
import com.ihsanfaiz0048.assesment1_mobpro.navigation.SetupNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(isDarkMode: Boolean, onToggleDarkMode: () -> Unit) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val topBarMenu = when (currentRoute) {
        Screen.BangunDatar.route -> stringResource(R.string.bangun_datar)
        Screen.BangunRuang.route -> stringResource(R.string.bangun_ruang)
        Screen.Settings.route -> stringResource(R.string.settings)
        else -> ""
    }
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    if (currentRoute == Screen.Settings.route) {
                        IconButton(
                            onClick = { navController.popBackStack() }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                },
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = topBarMenu
                        )
                    }
                },
                actions = {
                    if (currentRoute != Screen.Settings.route) {
                        IconButton(
                            onClick = {
                                navController.navigate(Screen.Settings.route) {
                                    popUpTo(Screen.Settings.route)
                                    launchSingleTop = true
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = stringResource(R.string.settings),
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            if (currentRoute != Screen.Settings.route)
                NavigationBar{

                    NavigationBarItem(
                        selected = currentRoute == Screen.BangunDatar.route,
                        onClick = {
                            navController.navigate(Screen.BangunDatar.route) {
                                popUpTo(Screen.BangunDatar.route)
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.bangun_datar_icon),
                                contentDescription = stringResource(R.string.bangun_datar),
                                modifier = Modifier.size(30.dp),
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        },
                        label = { Text(stringResource(R.string.bangun_datar)) }
                    )
                    NavigationBarItem(
                        selected = currentRoute == Screen.BangunRuang.route,
                        onClick = {
                            navController.navigate(Screen.BangunRuang.route) {
                                popUpTo(Screen.BangunRuang.route)
                                launchSingleTop = true
                            }
                        },
                        icon = { Icon(
                            painter = painterResource(R.drawable.bangun_ruang_icon),
                            contentDescription = stringResource(R.string.bangun_ruang),
                            modifier = Modifier.size(30.dp),
                            tint = MaterialTheme.colorScheme.onBackground
                        ) },
                        label = { Text(text = stringResource(R.string.bangun_ruang)) }
                    )
                }
        }
    ) {
            innerPadding ->
        SetupNavGraph (
            navController,
            modifier = Modifier.padding(innerPadding).padding(16.dp),
            isDarkMode = isDarkMode,
            onToggleDarkMode = onToggleDarkMode
        )
    }
}

@Composable
fun IconPicker(isError: Boolean){
    if (isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun ErrorHint(isError: Boolean){
    if (isError){
        Text(
            text = stringResource(R.string.input_invalid),
            color = MaterialTheme.colorScheme.error
        )
    }
}

private fun shareData(context: Context, message: String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null){
        context.startActivity(shareIntent)
    }
}

@Composable
fun ShareButton(context: Context, message: String){

    Button(
        onClick = { shareData(context, message) },
        modifier = Modifier.padding(top = 8.dp),
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
    ) {
        Text(text = stringResource(R.string.bagikan))
    }
}