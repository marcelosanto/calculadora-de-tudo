package com.developermarcelo.calculadora.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun Main() {


    var valorDoInput by remember { mutableStateOf("") }
    var texto by remember { mutableStateOf("") }
    var inputTemp by remember { mutableStateOf("") }
    val platform = getPlatformName()

    val regexFuncaoSegundoGrau = Regex("[+-]?[1-9]?[0-9]*x²[+-][0-9]*x[+-][0-9]+")

    Surface(modifier = Modifier.fillMaxSize()) {
        if (platform == "Android") Text(text = "Calculadora de Tudo")

        Column(
            modifier = Modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp, top = 40.dp),
            verticalArrangement = Arrangement.Top,
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
                listOf(
                    "C",
                    "x",
                    "²",
                    "/",
                    "7",
                    "8",
                    "9",
                    "*",
                    "4",
                    "5",
                    "6",
                    "-",
                    "1",
                    "2",
                    "3",
                    "+",
                    "0",
                    ".",
                    "<-",
                    "="
                )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(if (platform == "Android") 80.dp else 100.dp),
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
                        when (it) {
                            "=" -> {
                                texto = if (valorDoInput.matches(regexFuncaoSegundoGrau)) {
                                    equacaoDoSegundoGrau(valorDoInput)
                                } else "Error: Não reconhecido"
                            }

                            "C" -> valorDoInput = ""
                            "<-" -> {
                                inputTemp = valorDoInput
                                valorDoInput = inputTemp.dropLast(1)
                            }

                            else -> valorDoInput += it
                        }
                    }) {
                        Text(it)
                    }
                }
            }

        }
    }
}