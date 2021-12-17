package net.larntech.retrofit.model.response.users

data class UpdateUserResponse (
    val isSuccess: Int,
    val message: String,
    val username: String
)