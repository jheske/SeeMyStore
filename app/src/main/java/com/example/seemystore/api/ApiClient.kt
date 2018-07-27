/*
 * Created by Jill Heske on 7/26/2018
 * Copyright (c) All rights reserved
 */

package com.example.seemystore.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        val baseURL: String = "http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/"
        var retofit: Retrofit? = null

        val client: Retrofit
            get() {

                if (retofit == null) {
                    retofit = Retrofit.Builder()
                            .baseUrl(baseURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                }
                return retofit!!
            }
    }
}