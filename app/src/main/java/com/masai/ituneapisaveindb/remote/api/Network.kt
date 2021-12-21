package com.masai.ituneapisaveindb.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {
    //https://itunes.apple.com/

    companion object {
        val BaseUrl = "https://itunes.apple.com/"
        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}