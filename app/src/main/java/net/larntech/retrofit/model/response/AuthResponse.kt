package net.larntech.retrofit.model.response

data class AuthResponse(
    val isSuccess: Int,
    val message: String,
    val username: String
)