package com.ihsanfaiz0048.assesment1_mobpro.ui.screen.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.semantics.Role
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
fun LingkaranContent(bangunDatar: BangunDatar){
    var jariJari by rememberSaveable { mutableStateOf("") }
    var diameter by rememberSaveable { mutableStateOf("") }
    var hasilBangunDatar by remember { mutableStateOf<HasilBangunDatar?>(null) }
    var selectedOption by rememberSaveable { mutableStateOf("jari") }
    var diameterImage by rememberSaveable { mutableFloatStateOf(0F) }
    var jariJariImage by rememberSaveable { mutableFloatStateOf(0F) }
    var jariJariError by rememberSaveable { mutableStateOf(false) }
    var diameterError by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ){
        Row(
            modifier = Modifier.selectable(
                selected = selectedOption == "jari",
                onClick = { selectedOption = "jari" },
                role = Role.RadioButton
            ).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            RadioButton(
                selected = selectedOption == "jari",
                onClick = null,
            )
            Text(
                text = stringResource(R.string.jariJari)
            )
        }
        Row(
            modifier = Modifier.selectable(
                selected = selectedOption == "diameter",
                onClick = { selectedOption = "diameter" },
                role = Role.RadioButton
            ).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            RadioButton(
                selected = selectedOption == "diameter",
                onClick = null,
            )
            Text(
                text = stringResource(R.string.diameter)
            )
        }
    }
    when(selectedOption){
        "diameter" -> {
            OutlinedTextField(
                value = diameter,
                onValueChange = { diameter = it },
                trailingIcon = { IconPicker(diameterError) },
                supportingText = { ErrorHint(diameterError) },
                label = { Text(text = stringResource(R.string.diameter)) },
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
                            diameterError = (diameter == "0" || diameter == "")
                            if (diameterError) return@Button
                            val d = diameter.toFloatOrNull()
                            if (d != null){
                                val r = d / 2
                                hasilBangunDatar = bangunDatar.hitung(listOf(r))
                                diameterImage = d
                                jariJariImage = r
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
                            diameter = ""
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
                            painter = painterResource(R.drawable.lingkaran),
                            contentDescription = stringResource(R.string.lingkaran),
                            modifier = Modifier.size(250.dp),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = stringResource(R.string.diameter) + ": " + diameterImage,
                            modifier = Modifier.padding(bottom = 30.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(R.string.jariJari) + ": " + jariJariImage,
                            modifier = Modifier.padding(start = 120.dp).rotate(45f).padding(top = 150.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
        "jari" -> {
            OutlinedTextField(
                value = jariJari,
                onValueChange = { jariJari = it },
                trailingIcon = { IconPicker(jariJariError) },
                supportingText = { ErrorHint(jariJariError) },
                label = { Text(text = stringResource(R.string.jariJari)) },
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
                            jariJariError = (jariJari == "" || jariJari == "0")
                            if (jariJariError) return@Button
                            val p = jariJari.toFloatOrNull()
                            if (p != null){
                                val d = p * 2
                                hasilBangunDatar = bangunDatar.hitung(listOf(p))
                                jariJariImage = p
                                diameterImage = d
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
                            jariJari = ""
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
                            painter = painterResource(R.drawable.lingkaran),
                            contentDescription = stringResource(R.string.lingkaran),
                            modifier = Modifier.size(250.dp),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = stringResource(R.string.jariJari) + ": " + jariJariImage,
                            modifier = Modifier.padding(start = 120.dp).rotate(45f).padding(top = 150.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(R.string.diameter) + ": " + diameterImage,
                            modifier = Modifier.padding(bottom = 30.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}