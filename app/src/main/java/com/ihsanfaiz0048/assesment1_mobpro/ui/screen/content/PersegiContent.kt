package com.ihsanfaiz0048.assesment1_mobpro.ui.screen.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ihsanfaiz0048.assesment1_mobpro.R
import com.ihsanfaiz0048.assesment1_mobpro.model.BangunDatar
import com.ihsanfaiz0048.assesment1_mobpro.model.HasilBangunDatar
import com.ihsanfaiz0048.assesment1_mobpro.ui.screen.ErrorHint
import com.ihsanfaiz0048.assesment1_mobpro.ui.screen.IconPicker

@Composable
fun PersegiContent(bangunDatar: BangunDatar){
    var sisi by rememberSaveable { mutableStateOf("") }
    var hasilBangunDatar by remember { mutableStateOf<HasilBangunDatar?>(null) }
    var sisiImage by rememberSaveable { mutableFloatStateOf(0F) }
    var sisiError by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = sisi,
        onValueChange = { sisi = it },
        label = { Text(text = stringResource(R.string.sisi)) },
        trailingIcon = { IconPicker(sisiError) },
        supportingText = { ErrorHint(sisiError) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.padding(top = 16.dp))
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    sisiError = (sisi == "" || sisi == "0")
                    if (sisiError) return@Button
                    val p = sisi.toFloatOrNull()
                    if (p != null){
                        hasilBangunDatar = bangunDatar.hitung(listOf(p))
                        sisiImage = p
                    }
                },
                modifier = Modifier
            ) {
                Text(
                    text = stringResource(R.string.hitung)
                )
            }
            Button(
                onClick = {
                    sisi = ""
                    hasilBangunDatar = null
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(
                    text = stringResource(R.string.reset)
                )
            }
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
        if (hasilBangunDatar?.luas != null){
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = stringResource(R.string.luas, hasilBangunDatar?.luas?: 0.0),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = stringResource(R.string.keliling, hasilBangunDatar?.keliling?: 0.0),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(R.drawable.persegi),
                    contentDescription = stringResource(R.string.persegi),
                    modifier = Modifier.size(200.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = stringResource(R.string.sisi) + ": " + sisiImage,
                    modifier = Modifier.padding(bottom = 170.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.sisi) + ": " + sisiImage,
                    modifier = Modifier.padding(start = 200.dp).rotate(90f),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}