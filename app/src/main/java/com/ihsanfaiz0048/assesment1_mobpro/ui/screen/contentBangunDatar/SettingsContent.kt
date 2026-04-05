package com.ihsanfaiz0048.assesment1_mobpro.ui.screen.contentBangunDatar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.ihsanfaiz0048.assesment1_mobpro.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsContent(isDarkMode: Boolean, onToggleDarkMode: () -> Unit){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        onToggleDarkMode()
                    },
                    role = Role.Switch
                )
                .padding(8.dp)
        ){
            Text(
                text = "Dark Mode",
                style = MaterialTheme.typography.headlineSmall
            )
            Switch(
                checked = isDarkMode,
                onCheckedChange = { onToggleDarkMode() }
            )
        }
        Dividier()
        StaticSettingItem(stringResource(R.string.email), stringResource(R.string.devEmail))
        StaticSettingItem(stringResource(R.string.instagram), "@ihsanfa.iz")
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.version) + " " + stringResource(R.string.version_num),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun Dividier(){
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 8.dp),
        thickness = 1.dp
    )
}

@Composable
fun StaticSettingItem(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}