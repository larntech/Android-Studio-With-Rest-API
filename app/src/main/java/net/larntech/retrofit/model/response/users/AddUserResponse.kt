package net.larntech.retrofit.model.response.users

data class AddUserResponse (
    val isSuccess: Int,
    val message: String,
    val username: String
)