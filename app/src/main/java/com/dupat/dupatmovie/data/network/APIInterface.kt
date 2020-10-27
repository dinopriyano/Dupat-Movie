package com.dupat.dupatmovie.data.network

import com.dupat.dupatmovie.data.network.model.MovieModel
import com.dupat.dupatmovie.data.network.response.LoginResponse
import com.dupat.dupatmovie.data.network.response.MovieResponse
import com.dupat.dupatmovie.data.network.response.RegisterResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit
import java.util.stream.Stream.builder

interface APIInterface {

    @FormUrlEncoded
    @POST("login.php")
    suspend fun userLogin(
        @Field("username") username:String,
        @Field("password") password:String
    ) : Response<LoginResponse>

    @FormUrlEncoded
    @POST("register.php")
    suspend fun userRegister(
        @Field("username") username:String,
        @Field("password") password:String,
        @Field("name") name:String
    ) : Response<RegisterResponse>

    @GET("movie/top_rated")
    suspend fun getMovieList(
        @Query("api_key") api_key: String? = null,
        @Query("page") page: Int? = null
    ) : Response<MovieResponse>

    companion object{
        var client = OkHttpClient.Builder().apply {
            connectTimeout(30,TimeUnit.SECONDS)
            readTimeout(30,TimeUnit.SECONDS)
            writeTimeout(30,TimeUnit.SECONDS)
        }.build()

        operator fun invoke(baseUrl: String) : APIInterface{
            return Retrofit
                .Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIInterface::class.java)
        }

    }
}