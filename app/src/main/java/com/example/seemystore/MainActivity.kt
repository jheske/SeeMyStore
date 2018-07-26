package com.example.seemystore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.seemystore.R
import com.example.seemystore.api.ApiClient
import com.example.seemystore.api.ApiInterface

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    //https://antonioleiva.com/retrofit-android-kotlin/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = ApiClient.client.create(ApiInterface::class.java)
        val call = apiService.getStoresList()
    }


/*
    fun addSampleData() {
        StoreTable(this)
                .dbInsertStore(Store("Store1","1","Store1",
                        "Main Street","Atlanta","GA","30096",
                        "770-555-5555",28.078052F, -82.583301F))
        StoreTable(this)
                .dbInsertStore(Store("Store2","2","Store2",
                        "Main Street","Atlanta","GA","30096",
                        "770-555-5555",28.078052F, -82.583301F))
        StoreTable(this)
                .dbInsertStore(Store("Store3","3","Store3",
                        "Main Street","Atlanta","GA","30096",
                        "770-555-5555",28.078052F, -82.583301F))
        StoreTable(this)
                .dbInsertStore(Store("Store4","4","Store4",
                        "Main Street","Atlanta","GA","30096",
                        "770-555-5555",28.078052F, -82.583301F))
        StoreTable(this)
                .dbInsertStore(Store("Store5","5","Store5",
                        "Main Street","Atlanta","GA","30096",
                        "770-555-5555",28.078052F, -82.583301F))
        StoreTable(this)
                .dbInsertStore(Store("Store6","6","Store6",
                        "Main Street","Atlanta","GA","30096",
                        "770-555-5555",28.078052F, -82.583301F))
    }*/
}
