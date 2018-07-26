package com.example.seemystore

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seemystore.R
import com.example.seemystore.api.ApiClient
import com.example.seemystore.api.ApiInterface
import com.example.seemystore.api.StoreResponse
import com.example.seemystore.database.StoreDb
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
        getData()
        setupRecyclerView()
        displayStores()
    }

    private fun displayStores() {
        mStoreListAdapter.clear()
        mStoreListAdapter.addAll(mStoreList)
    }

    private fun getData() {
        val apiService = ApiClient.client.create(ApiInterface::class.java)
        val call = apiService.getStoresList()

        //https://android.jlelse.eu/keddit-part-6-api-retrofit-kotlin-d309074af0
        call.enqueue(object : Callback<StoreResponse> {
            override fun onResponse(call: Call<StoreResponse>, response: Response<StoreResponse>) {
                //var storeList: List<Store> = response.body()?.stores!!
                mStoreList = response.body()?.stores!!
                StoreTable(currentActivity).dbAddAllStores(mStoreList)
                Log.e(TAG, "Success!!!")
            }

            override fun onFailure(call: Call<StoreResponse>?, t: Throwable?) {
                //progressBar.visibility = View.GONE
                Log.e(TAG, t.toString())
            }
        })
    }

    private fun setupRecyclerView() {
        rv_store_list.setHasFixedSize(true)
        rv_store_list.layoutManager = LinearLayoutManager(mContext)
        rv_store_list.setNestedScrollingEnabled(false)
        mStoreListAdapter = StoreListAdapter(mStoreList)
        rv_store_list.adapter = mStoreListAdapter
    }

    private fun addStoreToAdapter(store: Store) {
        mStoreListAdapter.add(store)
    }
}
