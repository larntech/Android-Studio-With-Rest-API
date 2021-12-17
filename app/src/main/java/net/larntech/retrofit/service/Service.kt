package net.larntech.retrofit.service

import net.larntech.retrofit.model.response.AllUsersResponse
import net.larntech.retrofit.model.response.AuthResponse
import net.larntech.retrofit.model.response.RegisterUserResponse
import retrofit2.Call
import retrofit2.http.*

interface Service {

@FormUrlEncoded
@POST("auth/register.php")
fun registerUser(
    @Field("username") username: String,
    @Field("email") email: String,
    @Field("password") password:String
): Call<RegisterUserResponse>


@FormUrlEncoded
@POST("auth/login.php")
fun authenticateUser(
        @Field("username") username:String,
        @Field("password") password:String
): Call<AuthResponse>



@FormUrlEncoded
@POST("users/create.php")
fun addUser(
    @Field("username") username: String,
    @Field("password") password:String,
    @Field("expiry") expiry: String
): Call<RegisterUserResponse>


@FormUrlEncoded
@POST("users/update.php")
fun updateUser(
    @Field("id") id: String,
    @Field("username") username: String,
    @Field("password") password:String,
    @Field("deviceId") deviceId: String,
    @Field("expiry") expiry: String,
): Call<RegisterUserResponse>


@GET("users/getAll.php")
fun getAllUsers():Call<AllUsersResponse>


@FormUrlEncoded
@POST("users/delete.php")
    fun deleteUser(
        @Field("id") id: String,
): Call<RegisterUserResponse>

}