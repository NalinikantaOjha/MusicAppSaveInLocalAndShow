package com.masai.ituneapisaveindb.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    suspend fun getData(
        @Query("term") term:String): Response<com.masai.ituneapisaveindb.remote.Response>
}