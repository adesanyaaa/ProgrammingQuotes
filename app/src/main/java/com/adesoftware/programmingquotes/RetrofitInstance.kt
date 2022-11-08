package com.adesoftware.programmingquotes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: QuotesApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://programming-quotes-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuotesApi::class.java)
    }
}