package com.example.practice_coroutines

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        println("Weather forecast")
        println(getWeatherReport3())
        println("Have a good day!")
    }
}

suspend fun getWeatherReport3() = coroutineScope {
    val forecast = async { getForecast3() }
    val temperature = async {
        try {
            getTemperature3()
        } catch (e: AssertionError) {
            println("Caught exception $e")
            "{ No temperature found }"
        }
    }

    "${forecast.await()} ${temperature.await()}"
}

suspend fun getForecast3(): String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemperature3(): String {
    delay(500)
    throw AssertionError("Temperature is invalid")
    return "30\u00b0C"
}