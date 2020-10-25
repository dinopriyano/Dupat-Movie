package com.dupat.dupatmovie.data.network

import com.dupat.dupatmovie.data.network.response.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIInterface {

    @FormUrlEncoded
    @POST("login.php")
    suspend fun userLogin(
        @Field("username") username:String,
        @Field("password") password:String
    ) : Response<LoginResponse>

    companion object{
        operator fun invoke() : APIInterface{
            return Retrofit
                .Builder()
                .baseUrl("http://192.168.1.8/pc_jk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIInterface::class.java)
        }

    }
}