package net.larntech.retrofit.network.apiclient

import net.larntech.retrofit.network.service.Service
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun getRetrofit(): Retrofit{

        val logger = HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://xshooter.id/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();
        return retrofit;



    }

    fun getService(): Service {
        return getRetrofit().create(Service::class.java)
    }
}