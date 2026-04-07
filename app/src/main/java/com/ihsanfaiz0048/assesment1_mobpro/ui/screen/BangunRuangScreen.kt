package com.ihsanfaiz0048.assesment1_mobpro.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ihsanfaiz0048.assesment1_mobpro.R
import com.ihsanfaiz0048.assesment1_mobpro.model.BangunRuang
import com.ihsanfaiz0048.assesment1_mobpro.model.HasilBangunRuang
import com.ihsanfaiz0048.assesment1_mobpro.ui.screen.contentBangunRuang.BalokContent
import com.ihsanfaiz0048.assesment1_mobpro.ui.screen.contentBangunRuang.KerucutContent
import com.ihsanfaiz0048.assesment1_mobpro.ui.screen.contentBangunRuang.KubusContent
import com.ihsanfaiz0048.assesment1_mobpro.ui.screen.contentBangunRuang.TabungContent
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BangunRuangScreen(){
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by rememberSaveable {mutableStateOf("")}
    val listBangunRuang = listOf(
        BangunRuang(stringResource(R.string.kubus)){ input ->
            HasilBangunRuang(
                volume = input[0].pow(3),
                luasPermukaan = 6 * input[0].pow(2)
            )
        },

        BangunRuang(stringResource(R.string.balok)){input ->
            val panjang = input[0]
            val lebar = input[1]
            val tinggi = input[2]
            HasilBangunRuang(
                volume = panjang * lebar * tinggi,
                luasPermukaan = 2 * ((panjang * lebar)+(panjang * tinggi)+(lebar * tinggi))
            )
        },

        BangunRuang(stringResource(R.string.tabung)){input ->
            val r = input[0]
            val tinggi = input[1]
            HasilBangunRuang(
                volume = (PI * r.pow(2) * tinggi).toFloat(),
                luasPermukaan = (2 * PI * r * (tinggi + r)).toFloat()
            )
        },

        BangunRuang(stringResource(R.string.kerucut)){input ->
            val r = input[0]
            val tinggi = input[1]
            val s = sqrt(r.pow(2) + tinggi.pow(2))
            HasilBangunRuang(
                volume = ((1f / 3f) * PI * r.pow(2) * tinggi).toFloat(),
                luasPermukaan = (PI * r * (r + s)).toFloat()
            )
        },

        BangunRuang(stringResource(R.string.bola)){input ->
            val r = input[0]
            HasilBangunRuang(
                volume = ((4f / 3f) * PI * r.pow(3)).toFloat(),
                luasPermukaan = (4f * PI * r.pow(2)).toFloat()
            )
        }
    )

    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())) {
        Text(
            text = stringResource(R.string.intro_bangun_ruang),
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
                label = { Text(text = stringResource(R.string.pilih_bangun_ruang)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listBangunRuang.forEach { bangunRuang ->
                    DropdownMenuItem(
                        text = { Text(text = bangunRuang.nama) },
                        onClick = {
                            selectedOption = bangunRuang.nama
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        when (selectedOption) {
            listBangunRuang[0].nama -> {
                KubusContent(listBangunRuang[0])
            }

            listBangunRuang[1].nama -> {
                BalokContent(listBangunRuang[1])
            }

            listBangunRuang[2].nama -> {
                TabungContent(listBangunRuang[2])
            }

            listBangunRuang[3].nama -> {
                KerucutContent(listBangunRuang[3])
            }
//            listBangunRuang[3].nama -> {
//                KerucutContent(listBangunRuang[3])
//            }
        }
    }
}

