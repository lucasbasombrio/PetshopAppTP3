package com.example.petshopapptp3.network

data class LoginResponse(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val token: String
)
