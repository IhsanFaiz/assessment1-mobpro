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
fun PersegiPanjangContent(bangunDatar: BangunDatar){
    var panjang by rememberSaveable { mutableStateOf("") }
    var lebar by rememberSaveable { mutableStateOf("") }
    var hasilBangunDatar by remember { mutableStateOf<HasilBangunDatar?>(null) }
    var panjangImage by rememberSaveable { mutableFloatStateOf(0F) }
    var lebarImage by rememberSaveable { mutableFloatStateOf(0F) }
    var panjangError by rememberSaveable { mutableStateOf(false) }
    var lebarError by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = panjang,
        onValueChange = { panjang = it },
        trailingIcon = { IconPicker(panjangError) },
        supportingText = { ErrorHint(panjangError) },
        label = { Text(text = stringResource(R.string.panjang)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = lebar,
        onValueChange = { lebar = it },
        trailingIcon = { IconPicker(lebarError) },
        supportingText = { ErrorHint(lebarError) },
        singleLine = true,
        label = { Text(text = stringResource(R.string.lebar)) },
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
                    panjangError = (panjang == "" || panjang == "0")
                    lebarError = (lebar == "" || lebar == "0")
                    if (panjangError || lebarError)return@Button
                    val p = panjang.toFloatOrNull()
                    val l = lebar.toFloatOrNull()
                    if (p != null && l != null){
                        hasilBangunDatar = bangunDatar.hitung(listOf(p, l))
                        panjangImage = p
                        lebarImage = l
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
                    panjang = ""
                    lebar = ""
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
                    painter = painterResource(R.drawable.persegi_panjang),
                    contentDescription = stringResource(R.string.persegi_panjang),
                    modifier = Modifier.size(200.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = stringResource(R.string.lebar) + ": " + lebarImage,
                    modifier = Modifier.padding(start = 180.dp).rotate(90f),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.panjang) + ": " + panjangImage,
                    modifier = Modifier.padding(bottom = 150.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}