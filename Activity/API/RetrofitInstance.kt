package com.example.foodbuddy.API

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import java.net.HttpCookie

interface ApiInterface {
    @Headers("Content-Type:application/json")
    @POST("api/login")
    fun signin(
            @Body info: SignInBody
    ): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("api/register")
    fun registerUser(
        @Body info: UserBody
    ): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("api/addFood")
    fun addFood(
            @Body info: AddFoodBody
    ): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("api/deleteFood")
    fun deleteFood(
            @Body info: DeleteFoodBody
    ): retrofit2.Call<ResponseBody>

}

class RetrofitInstance {
    companion object {
        val BASE_URL: String = "https://group1largeproject.herokuapp.com/"

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
