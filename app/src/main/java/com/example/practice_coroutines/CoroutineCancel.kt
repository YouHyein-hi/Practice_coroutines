package com.example.practice_coroutines

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        println("Weather forecast")
        println(getWeatherReport4())
        println("Have a good day!")
    }
}

suspend fun getWeatherReport4() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async { getTemperature() }

    delay(200)
    temperature.cancel() // Sunny만 출력되고 Have a good day! 출력

    forecast.await()
}