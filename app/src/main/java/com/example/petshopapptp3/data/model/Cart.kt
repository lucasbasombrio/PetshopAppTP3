package com.example.petshopapptp3.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CartProduct(
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val total: Double,
    val thumbnail: String
)

@Serializable
data class CartResponse(
    val id: Int,
    val products: List<CartProduct>,
    val total: Double,
    val discountedTotal: Double,
    val totalProducts: Int,
    val totalQuantity: Int
)
