package com.ihsanfaiz0048.assesment1_mobpro.ui.screen.contentBangunRuang

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
import com.ihsanfaiz0048.assesment1_mobpro.model.BangunRuang
import com.ihsanfaiz0048.assesment1_mobpro.model.HasilBangunRuang
import com.ihsanfaiz0048.assesment1_mobpro.ui.screen.ErrorHint
import com.ihsanfaiz0048.assesment1_mobpro.ui.screen.IconPicker

@Composable
fun BalokContent(bangunRuang: BangunRuang){
    var panjang by rememberSaveable { mutableStateOf("") }
    var lebar by rememberSaveable { mutableStateOf("") }
    var tinggi by rememberSaveable { mutableStateOf("") }
    var hasilBangunRuang by remember { mutableStateOf<HasilBangunRuang?>(null) }
    var panjangImage by rememberSaveable { mutableFloatStateOf(0F) }
    var lebarImage by rememberSaveable { mutableFloatStateOf(0F) }
    var tinggiImage by rememberSaveable { mutableFloatStateOf(0F) }
    var panjangError by rememberSaveable { mutableStateOf(false) }
    var lebarError by rememberSaveable { mutableStateOf(false) }
    var tinggiError by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = panjang,
        onValueChange = { panjang = it },
        label = { Text(text = stringResource(R.string.panjang)) },
        trailingIcon = { IconPicker(panjangError) },
        supportingText = { ErrorHint(panjangError) },
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
        label = { Text(text = stringResource(R.string.lebar)) },
        trailingIcon = { IconPicker(lebarError) },
        supportingText = { ErrorHint(lebarError) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = tinggi,
        onValueChange = { tinggi = it },
        label = { Text(text = stringResource(R.string.tinggi)) },
        trailingIcon = { IconPicker(tinggiError) },
        supportingText = { ErrorHint(tinggiError) },
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
                    panjangError = (panjang == "" || panjang == "0")
                    if (panjangError) return@Button
                    lebarError = (lebar == "" || lebar == "0")
                    if (lebarError) return@Button
                    tinggiError = (tinggi == "" || tinggi == "0")
                    if (tinggiError) return@Button
                    val p = panjang.toFloatOrNull()
                    val l = lebar.toFloatOrNull()
                    val t = tinggi.toFloatOrNull()
                    if (p != null && l != null && t != null){
                        hasilBangunRuang = bangunRuang.hitung(listOf(p, l, t))
                        panjangImage = p
                        lebarImage = l
                        tinggiImage = t
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
                    tinggi = ""
                    hasilBangunRuang = null
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
        if (hasilBangunRuang?.volume != null){
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = stringResource(R.string.volume, hasilBangunRuang?.volume?: 0.0),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = stringResource(R.string.luas_permukaan, hasilBangunRuang?.luasPermukaan?: 0.0),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(R.drawable.balok),
                    contentDescription = stringResource(R.string.balok),
                    modifier = Modifier.size(250.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = stringResource(R.string.panjang) + ": " + panjangImage,
                    modifier = Modifier.padding(top = 160.dp, start = 90.dp).rotate(-23f),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.lebar) + ": " + lebarImage,
                    modifier = Modifier.padding(end = 160.dp, top = 160.dp).rotate(25f),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.tinggi) + ": " + tinggiImage,
                    modifier = Modifier.padding(start = 270.dp).rotate(95f),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}