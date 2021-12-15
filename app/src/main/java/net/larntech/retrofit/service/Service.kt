package net.larntech.retrofit.service

import net.larntech.retrofit.model.response.AuthResponse
import net.larntech.retrofit.model.response.RegisterUserResponse
import retrofit2.Call
import retrofit2.http.*

interface Service {

@FormUrlEncoded
@POST("register.php")
fun registerUser(
    @Field("username") username: String,
    @Field("email") email: String,
    @Field("password") password:String
): Call<RegisterUserResponse>

@FormUrlEncoded
@POST("login.php")
fun authenticateUser(
    @Field("username") username:String,
    @Field("password") password:String
): Call<AuthResponse>


}