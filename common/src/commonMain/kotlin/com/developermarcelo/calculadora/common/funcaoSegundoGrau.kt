package com.developermarcelo.calculadora.common

import kotlin.math.sqrt

fun equacaoDoSegundoGrau(valor: String): String {
    var tempValor = valor.replace(Regex("(?<![0-9])x"), "1x")
    val valores = arrayListOf<Int>()

    Regex("[+-]?[0-9]+").findAll(tempValor).forEach {
        valores.add(it.value.toInt())
    }

    val (valorA, valorB, valorC) = valores
    val multi = resolverMutiplicacao(valorA, valorC)
    val elevar = elevar(valorB)
    val delta = resolver(elevar, multi)
    val raizes = calcularRaizes(valorA, valorB, delta)

    return "Δ = $delta, $raizes"
}

fun calcularRaizes(valorA: Int, valorB: Int, delta: Int): String {
    val x1 = ((valorB * -1) + sqrt(delta.toDouble())) / (2 * valorA)
    val x2 = ((valorB * -1) - sqrt(delta.toDouble())) / (2 * valorA)

    return "x1: ${x1.toInt()}, x2: ${x2.toInt()}"
}

fun resolver(valorB: Int, valor: Int): Int = valorB + valor

fun elevar(valor: Int): Int = valor * valor


fun resolverMutiplicacao(valorA: Int, valorC: Int): Int = -4 * valorA * valorC




