package com.example.allmighty.calculator.presentation.model

enum class TrumpSuit {
    스페이드, 다이아, 하트, 클로버, 노기루
}

fun String.findTrumpSuit() : TrumpSuit? {
    return TrumpSuit.entries.find { it.name == this }
}