package net.larntech.retrofit.network.service

import net.larntech.retrofit.model.response.users.AddUserResponse
import net.larntech.retrofit.model.response.users.AllUsersResponse
import net.larntech.retrofit.model.response.users.DeleteUserResponse
import net.larntech.retrofit.model.response.auth.LoginResponse
import net.larntech.retrofit.model.response.auth.RegisterUserResponse
import net.larntech.retrofit.model.response.users.UpdateUserResponse
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
): Call<LoginResponse>



@FormUrlEncoded
@POST("users/add.php")
fun addUser(
    @Field("username") username: String,
    @Field("password") password:String,
    @Field("expiry") expiry: String
): Call<AddUserResponse>


@FormUrlEncoded
@POST("users/update.php")
fun updateUser(
    @Field("id") id: String,
    @Field("username") username: String,
    @Field("password") password:String,
    @Field("deviceId") deviceId: String,
    @Field("expiry") expiry: String,
): Call<UpdateUserResponse>


@GET("users/getAll.php")
fun getAllUsers():Call<AllUsersResponse>


@FormUrlEncoded
@POST("users/delete.php")
    fun deleteUser(
        @Field("id") id: String,
): Call<DeleteUserResponse>

}