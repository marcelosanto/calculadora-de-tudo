package com.developermarcelo.calculadora.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun App() {

    var valorDoInput by remember { mutableStateOf("") }
    var texto by remember { mutableStateOf("") }
    var inputTemp by remember { mutableStateOf("") }
    val platform = getPlatformName()

    val regexFuncaoSegundoGrau = Regex("[+-]?[1-9]?[0-9]*x²[+-][0-9]*x[+-][0-9]+")

    Surface(modifier = Modifier.fillMaxSize().padding(16.dp), color = Color.Gray) {
        if (platform == "Android") Text(text = "Calculadora de Tudo")

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                value = TextFieldValue(
                    text = valorDoInput,
                    selection = TextRange(valorDoInput.length)
                ),
                onValueChange = {
                    valorDoInput = it.text.trim()
                },
            )

            Text(text = texto)

            val botoesDaCalculadora =
                listOf("x²", "x", "/", "-", "7", "8", "9", "*", "4", "5", "6", "+", "1", "2", "3", "=")

            Button(onClick = {
                inputTemp = valorDoInput
                valorDoInput = inputTemp.dropLast(1)
            }) {
                Text("<-")
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(100.dp),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 16.dp,
                    end = 12.dp,
                    bottom = 16.dp
                )
            )
            {
                items(botoesDaCalculadora) {
                    Button(modifier = Modifier.padding(4.dp), onClick = {
                        if (it == "=") {
                            texto = if (valorDoInput.matches(regexFuncaoSegundoGrau)) {
                                funcaoSegundoGrau(valorDoInput)
                            } else "$valorDoInput não é uma função do segundo grau"
                        } else valorDoInput += it
                    }) {
                        Text(it)
                    }
                }
            }

        }
    }

}
