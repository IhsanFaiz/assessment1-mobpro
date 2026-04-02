package com.ihsanfaiz0048.assesment1_mobpro.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ihsanfaiz0048.assesment1_mobpro.R
import com.ihsanfaiz0048.assesment1_mobpro.model.BangunDatar
import com.ihsanfaiz0048.assesment1_mobpro.model.Hasil
import kotlin.math.sqrt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BangunDatarScreen(){
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember {mutableStateOf("")}
    val listBangunDatar = listOf(

        BangunDatar(stringResource(R.string.persegi_panjang)) { input ->
            Hasil(
                luas = input[0] * input[1],
                keliling = 2 * (input[0] + input[1])
            )
        },
        BangunDatar(stringResource(R.string.persegi)){ input ->
            Hasil(
                luas = input[0] * input[0],
                keliling = 4 * input[0]
            )
        },
        BangunDatar(stringResource(R.string.lingkaran)){ input ->
            Hasil(
                luas = (Math.PI * input[0] * input[0]).toFloat(),
                keliling = (2 * Math.PI * input[0]).toFloat()
            )
        },
        BangunDatar(stringResource(R.string.segitiga)){ input ->
            val a = input[0]
            val b = input[1]
            val c = input[2]

            if (a + b > c && a + c > b && b + c > a) {

                val s = (a + b + c) / 2

                Hasil(
                    luas = sqrt(s * (s - a) * (s - b) * (s - c)),
                    keliling = a + b + c
                )

            } else {
                Hasil(
                    luas = 0f,
                    keliling = 0f
                )
            }
        }
    )
    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())){
        Text(
            text = stringResource(R.string.intro_bangun_datar),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                label = { Text( text = stringResource(R.string.pilih_bangun_datar)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ){
                listBangunDatar.forEach { bangunDatar ->
                    DropdownMenuItem(
                        text = { Text(text = bangunDatar.nama) },
                        onClick = {
                            selectedOption = bangunDatar.nama
                            expanded = false
                        }
                    )
                }
            }
        }
//        Spacer(modifier = Modifier.padding(bottom = 16.dp))
//        when (selectedOption) {
//            listBangunDatar[0].nama -> {
//                PersegiPanjangContent(listBangunDatar[0])
//            }
//            listBangunDatar[1].nama -> {
//                PersegiContent(listBangunDatar[1])
//            }
//            listBangunDatar[2].nama -> {
//                LingkaranContent(listBangunDatar[2])
//            }listBangunDatar[3].nama -> {
//            SegitigaContent(listBangunDatar[3])
//        }
//        }
    }
}