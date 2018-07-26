package com.example.seemystore.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.view.MenuItem
import com.example.seemystore.R
import com.example.seemystore.Store
import com.example.seemystore.database.StoreTable
import com.example.seemystore.setToolbarLogo
import com.example.seemystore.setupToolbar
import kotlinx.android.synthetic.main.activity_store_detail.*
import retrofit2.http.Url

const val STORE_ID_EXTRA = "STORE_ID_EXTRA"

class StoreActivity : AppCompatActivity() {
    private val TAG = Store::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)
        val storeId = intent?.getStringExtra(STORE_ID_EXTRA)
        val store =   StoreTable(this).dbGetStore(storeId)
        store?.let {
            setupToolbar(true)
            setupViews(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Respond to the action bar's Up/Home button
                //Return true to indicate the option has been handled
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
            else -> return false
        }
    }

    private fun setupViews(store: Store) {
        store.apply {
            tv_name.text = name
            tv_address.text = address
        }
    }


}
