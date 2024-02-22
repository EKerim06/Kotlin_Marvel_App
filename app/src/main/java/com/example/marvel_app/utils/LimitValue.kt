package com.example.marvel_app.utils

enum class LimitValue {
    NORMAL;
}

fun LimitValue.getValue(): Int {
    return when (this) {
        LimitValue.NORMAL -> 30
    }
}

fun main() {
    val apiKeyValue = ApiKey.API_KEY.getValue()
    println("API Key Value: $apiKeyValue")
}
