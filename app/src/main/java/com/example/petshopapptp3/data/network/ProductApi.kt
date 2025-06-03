package com.example.petshopapptp3.data.network

import com.example.petshopapptp3.data.model.ProductResponse
import com.example.petshopapptp3.data.model.ProductDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDetailResponse
}
