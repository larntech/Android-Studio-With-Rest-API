package net.larntech.retrofit.service

import net.larntech.retrofit.model.request.AuthRequest
import net.larntech.retrofit.model.request.RegisterUserRequest
import net.larntech.retrofit.model.response.AuthResponse
import net.larntech.retrofit.model.response.RegisterUserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Service {

@POST("/users/")
fun registerUser(@Body registerRequest: RegisterUserRequest): Call<RegisterUserResponse> ;

@POST("/authenticate/")
fun authenticateUser(@Body authRequest: AuthRequest): Call<AuthResponse>


}