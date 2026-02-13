package com.example.viikkotehtava5.data.repository

import com.example.viikkotehtava5.BuildConfig
import com.example.viikkotehtava5.data.model.WeatherResponse
import com.example.viikkotehtava5.data.remote.RetrofitClient
import com.example.viikkotehtava5.utils.Result
import retrofit2.HttpException
import java.io.IOException

class WeatherRepository {

    private val apiService = RetrofitClient.weatherApiService

    private val apiKey = BuildConfig.OPENWEATHER_API_KEY

    suspend fun getWeather(city: String): Result<WeatherResponse> {
        return try {
            val response = apiService.getWeather(city, apiKey)
            Result.Success(response)
        } catch (e: IOException) {
            Result.Error(Exception("Verkkovirhe: ${e.message}"))
        } catch (e: HttpException) {
            Result.Error(Exception("Palvelinvirhe: ${e.code()}"))
        } catch (e: Exception) {
            Result.Error(Exception("Tuntematon virhe: ${e.message}"))
        }
    }
}