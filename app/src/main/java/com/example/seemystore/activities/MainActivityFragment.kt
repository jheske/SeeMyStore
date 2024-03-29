/*
 * Created by Jill Heske on 7/26/2018
 * Copyright (c) All rights reserved
 */

package com.example.seemystore.activities

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.example.seemystore.*
import com.example.seemystore.api.ApiClient
import com.example.seemystore.api.ApiInterface
import com.example.seemystore.api.StoreResponse
import com.example.seemystore.database.StoreTable
import kotlinx.android.synthetic.main.fragment_activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityFragment : Fragment() {
    private val TAG = MainActivityFragment::class.java.simpleName
    lateinit var mContext: Context
    private var mStoreList: MutableList<Store> = ArrayList()
    lateinit var mStoreListAdapter: StoreListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_activity_main, container, false)
         return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onResume() {
        super.onResume()
        mStoreList = StoreTable(mContext).dbGetAllStores()
        if (currentActivity.applicationContext.isNetworkAvailable()) {
            tv_network_error.visibility = GONE
            rv_store_list.visibility = VISIBLE
            getData()
        } else {
            tv_network_error.visibility = VISIBLE
            rv_store_list.visibility = GONE
        }
    }

    private fun displayStores() {
        mStoreListAdapter.clear()
        mStoreListAdapter.addAll(mStoreList)
    }

    private fun getData() {
        val apiService = ApiClient.client.create(ApiInterface::class.java)
        val call = apiService.getStoresList()

        call.enqueue(object : Callback<StoreResponse> {
            override fun onResponse(call: Call<StoreResponse>, response: Response<StoreResponse>) {
                mStoreList = response.body()?.stores!!
                StoreTable(currentActivity).dbAddAllStores(mStoreList)
                setupRecyclerView()
                displayStores()
                Log.e(TAG, "Success!!!")
            }

            override fun onFailure(call: Call<StoreResponse>?, t: Throwable?) {
                Log.e(TAG, t.toString())
            }
        })
    }

    private fun setupRecyclerView() {
        rv_store_list.setHasFixedSize(true)
        rv_store_list.layoutManager = LinearLayoutManager(mContext)
        rv_store_list.setNestedScrollingEnabled(false)
        mStoreListAdapter = StoreListAdapter()
        rv_store_list.adapter = mStoreListAdapter
    }

}
