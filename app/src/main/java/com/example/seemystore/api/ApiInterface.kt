/*
 * Created by Jill Heske on 7/26/2018
 * Copyright (c) All rights reserved
 */

package com.example.seemystore.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("stores.json")
    fun getStoresList(): Call<StoreResponse>
}