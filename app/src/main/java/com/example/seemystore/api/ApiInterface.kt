package com.example.seemystore.api

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by jill
 */
interface ApiInterface {
    @GET("stores.json")
    fun getStoresList(): Call<StoreResponse>
}