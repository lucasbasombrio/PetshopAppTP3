package com.example.petshopapptp3.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val thumbnail: String
)

@Serializable
data class ProductResponse(val products: List<Product>)

object ProductService {

    // Cliente global bien configurado
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // <-- Necesario para evitar errores con campos extra como 'description'
            })
        }
    }

    suspend fun getProducts(): List<Product> {
        return try {
            val response: HttpResponse = client.get("https://dummyjson.com/products")
            val productResponse: ProductResponse = response.body()
            productResponse.products
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}

