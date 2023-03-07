package com.developermarcelo.calculadora.common

import kotlin.math.sqrt

fun funcaoSegundoGrau(valor: String): String {
    var tempValor = valor.replace(Regex("(?<![0-9])x"), "1x")

    val valorA = Regex("[+-]?[0-9]+").find(tempValor)?.value
    tempValor = tempValor.replaceFirst(valorA!!, "")

    val valorB = Regex("[+-][0-9]+").find(tempValor)?.value
    tempValor = tempValor.replace(valorB!!, "")

    val valorC = Regex("[+-][0-9]+").find(tempValor)?.value

    val multi = valorC?.let { resolverMutiplicacao(valorA, it) } ?: 0


    val elevarNumero = elevado(valorB)

    val delta = resolver(elevarNumero, multi)

    println("Delta = $delta")


    //val delta = "Δ = $result"

    return "Δ = $delta, ${calcularRaizes(valorA, valorB, delta)}"
}

fun calcularRaizes(valordeA: String, valordeB: String, delta: Int): String {
    val raizQuadrada = sqrt(delta.toDouble())
    val valorB = valordeB.toInt() * -1

    val x1 = (valorB + raizQuadrada) / (2 * valordeA.toInt())
    val x2 = (valorB - raizQuadrada) / (2 * valordeA.toInt())

    return "x1: ${x1.toInt()}, x2: ${x2.toInt()}"
}

fun resolver(valorB: Int, valor: Int): Int = valorB + valor

fun elevado(valor: String): Int = valor.toInt() * valor.toInt()


fun resolverMutiplicacao(valorA: String, valorC: String): Int {
    val numeros = Regex("[+-]?[0-9]+")

    var resul = 1

    numeros.findAll("-4 $valorA $valorC").forEach { matchResult ->
        resul *= matchResult.value.toInt()
    }

    return resul
}