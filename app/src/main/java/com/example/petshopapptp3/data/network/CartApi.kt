package com.example.petshopapptp3.data.network

import com.example.petshopapptp3.data.model.CartResponse
import retrofit2.http.GET

interface CartApi {
    @GET("carts/1")
    suspend fun getCart(): CartResponse
}
