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
import com.ihsanfaiz0048.assesment1_mobpro.model.Hasil
import com.ihsanfaiz0048.assesment1_mobpro.ui.screen.ErrorHint
import com.ihsanfaiz0048.assesment1_mobpro.ui.screen.IconPicker

@Composable
fun SegitigaContent(bangunDatar: BangunDatar){
    var sisi1 by remember { mutableStateOf("") }
    var sisi2 by remember { mutableStateOf("") }
    var sisi3 by remember { mutableStateOf("") }
    var sisi1Error by remember { mutableStateOf(false) }
    var sisi2Error by remember { mutableStateOf(false) }
    var sisi3Error by remember { mutableStateOf(false) }
    var hasil by remember { mutableStateOf<Hasil?>(null) }
    var sisi1Image by remember { mutableFloatStateOf(0F) }
    var sisi2Image by remember { mutableFloatStateOf(0F) }
    var sisi3Image by remember { mutableFloatStateOf(0F) }

    OutlinedTextField(
        value = sisi1,
        onValueChange = { sisi1 = it },
        trailingIcon = { IconPicker(sisi1Error) },
        supportingText = { ErrorHint(sisi1Error) },
        label = { Text(text = stringResource(R.string.sisi1)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = sisi2,
        onValueChange = { sisi2 = it },
        trailingIcon = { IconPicker(sisi2Error) },
        supportingText = { ErrorHint(sisi2Error) },
        singleLine = true,
        label = { Text(text = stringResource(R.string.sisi2)) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = sisi3,
        onValueChange = { sisi3 = it },
        trailingIcon = { IconPicker(sisi3Error) },
        supportingText = { ErrorHint(sisi3Error) },
        singleLine = true,
        label = { Text(text = stringResource(R.string.sisi3)) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()
    )
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
                    sisi1Error = (sisi1 == "0" || sisi1 == "")
                    sisi2Error = (sisi2 == "0" || sisi2 == "")
                    sisi3Error = (sisi3 == "0" || sisi3 == "")
                    if (sisi1Error || sisi2Error || sisi3Error) return@Button
                    val p = sisi1.toFloatOrNull()
                    val l = sisi2.toFloatOrNull()
                    val t = sisi3.toFloatOrNull()
                    if (p != null && l != null && t != null){
                        hasil = bangunDatar.hitung(listOf(p, l, t))
                        sisi1Image = p
                        sisi2Image = l
                        sisi3Image = t
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
                    sisi1 = ""
                    sisi2 = ""
                    sisi3 = ""
                    hasil = null
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
        if (hasil?.luas != null){
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = stringResource(R.string.luas, hasil?.luas?: 0.0),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = stringResource(R.string.keliling, hasil?.keliling?: 0.0),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(R.drawable.segitiga),
                    contentDescription = stringResource(R.string.segitiga),
                    modifier = Modifier.size(200.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = stringResource(R.string.sisi1) + ": " + sisi1Image,
                    modifier = Modifier.padding(start = 130.dp).rotate(60f),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.sisi2) + ": " + sisi2Image,
                    modifier = Modifier.padding(end = 130.dp).rotate(-60f),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.sisi3) + ": " + sisi3Image,
                    modifier = Modifier.padding(top = 130.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}