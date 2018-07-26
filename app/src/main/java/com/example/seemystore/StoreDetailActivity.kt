package com.example.seemystore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.seemystore.R
import com.example.seemystore.database.StoreTable
import kotlinx.android.synthetic.main.activity_store_detail.*

const val STORE_ID_EXTRA = "STORE_ID_EXTRA"

class StoreActivity : AppCompatActivity() {
    private val TAG = Store::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)
        val storeId = intent?.getStringExtra(STORE_ID_EXTRA)
        val store =   StoreTable(this).dbGetStore(storeId)
        setTitle(title)
        store?.let {
            setupViews(it)
        }
    }

    private fun setupViews(store: Store) {
        store.apply {
            tv_name.text = name
            tv_address.text = address
        }
    }


}
