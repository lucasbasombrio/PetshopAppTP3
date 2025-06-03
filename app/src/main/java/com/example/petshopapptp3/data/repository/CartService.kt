package com.example.petshopapptp3.data.repository

import com.example.petshopapptp3.data.model.CartResponse
import com.example.petshopapptp3.data.network.CartApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CartService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(CartApi::class.java)

    suspend fun getCart(): CartResponse {
        return api.getCart()
    }
}
