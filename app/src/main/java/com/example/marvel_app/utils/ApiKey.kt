package com.example.marvel_app.utils

enum class ApiKey {
    API_KEY;
}

fun ApiKey.getValue(): String {
    return when (this) {
        ApiKey.API_KEY -> "d9011c51673cfc4f09216f23b618c1b0"
    }
}

fun main() {
    val apiKeyValue = ApiKey.API_KEY.getValue()
    println("API Key Value: $apiKeyValue")
}
