package com.example.seemystore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.seemystore.R
import com.example.seemystore.api.ApiClient
import com.example.seemystore.api.ApiInterface

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
