package com.example.petshopapptp3.network

data class LoginRequest(
    val username: String,
    val password: String,
    val expiresInMins: Int = 30
)
