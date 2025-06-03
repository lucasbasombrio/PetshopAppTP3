package com.example.petshopapptp3.data.repository

import com.example.petshopapptp3.data.model.ProductResponse
import com.example.petshopapptp3.data.model.Product
import com.example.petshopapptp3.data.network.ProductApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(ProductApi::class.java)

    suspend fun getProducts(): List<Product> {
        return api.getProducts().products
    }
}
