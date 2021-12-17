package net.larntech.retrofit.model.response.auth

data class RegisterUserResponse(
   val isSuccess: Int,
   val message: String,
   val username: String
)
