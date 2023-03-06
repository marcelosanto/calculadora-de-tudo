package com.developermarcelo.calculadora.common

fun funcaoSegundoGrau(valor: String): String {
    /**
     * Para resolver uma equação do segundo grau, há vários métodos,
     * como a fórmula de Bhaskara e a soma e produto.
     * vamos calcular o DELTA
     * formula -> Δ = b² – 4ac
     * ex ->  2x²+8x–24
     * -> A: 2 - B: 8 - C : -24
     * -> Δ = 8² – 4 * 2 * (-24)
     * */
    var tempValor = valor

    val valorA = Regex("[+-]?[0-9]+").find(tempValor)?.value
    tempValor = tempValor.replaceFirst(valorA!!, "")

    val valorB = Regex("[+-][0-9]+").find(tempValor)?.value
    tempValor = tempValor.replace(valorB!!, "")

    val valorC = Regex("[+-][0-9]+").find(tempValor)?.value

    /**
     * 2x² + 8x – 24
     * Δ = b² [– 4ac]
     * Δ = 8² – 4·2·(– 24)
     * Δ = 64 + 192
     * Δ = 256
     * */

    val mult = if (valorC != null) {
        resolverMutiplicacao(valorA, valorC)
    } else "Error"

    val bElevadoB = elevado(valorB)

    val result = resolver("$bElevadoB $mult")

    return "Δ = $result"
}

fun resolver(s: String): String {
    val splitCount = s.split(" ").filter { it != "" }
    var result = 0

    //[64, +, 192]
    // 0   1   2

    when (splitCount[1]) {
        "+" -> {
            result = splitCount[0].toInt() + splitCount[2].toInt()
        }

        "-" -> {
            result = splitCount[0].toInt() - splitCount[2].toInt()
        }
    }

    return result.toString()
}

fun elevado(valorB: String): Int {
    val valor = Regex("[0-9]+").find(valorB)?.value?.toInt() ?: 0
    return valor * valor
}

fun resolverMutiplicacao(valorA: String, valorC: String): String {
    val valA = porSinal(valorA)

    val sinal = if (resolverSinais("-4$valA$valorC") % 2 == 0) "+" else "-"
    println("-4$valA$valorC")

    val numeros = Regex("[0-9]+")

    var resul = 1

    numeros.findAll("-4$valA$valorC").forEach { matchResult ->
        resul *= matchResult.value.toInt()
    }

    println("result = $resul")

    return "$sinal $resul"
}

fun porSinal(valor: String): String = if (valor.matches(Regex("[+-][0-9]+"))) {
    println("porSinal: $valor")
    valor
} else "+$valor"


fun resolverSinais(value: String) = value.count { it == '-' }