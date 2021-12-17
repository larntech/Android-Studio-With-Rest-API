package net.larntech.retrofit.model.response.auth

data class LoginResponse(
    val isSuccess: Int,
    val message: String,
    val username: String
)