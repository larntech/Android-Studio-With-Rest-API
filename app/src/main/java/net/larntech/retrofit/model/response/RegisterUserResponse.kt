package net.larntech.retrofit.model.response

data class RegisterUserResponse(
   val isSuccess: Int,
   val message: String,
   val username: String
)
