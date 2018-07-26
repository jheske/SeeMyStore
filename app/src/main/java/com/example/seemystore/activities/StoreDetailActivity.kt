package com.example.seemystore.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.view.MenuItem
import com.example.seemystore.R
import com.example.seemystore.Store
import com.example.seemystore.database.StoreTable
import com.example.seemystore.setupToolbar
import com.squareup.phrase.Phrase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_store_detail.*

const val STORE_ID_EXTRA = "STORE_ID_EXTRA"

class StoreActivity : AppCompatActivity() {
    private val TAG = Store::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)
        val storeId = intent?.getStringExtra(STORE_ID_EXTRA)
        val store =   StoreTable(this).dbGetStore(storeId)
        store?.let {
            setupToolbar(store.name,true)
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
            Picasso.get()
                    .load(storeLogoURL)
                    .into(img_store)

            val welcome = Phrase.from(getString(R.string.txt_welcome))
                    .put("store_name", name)
                    .format()
                    .toString()
            tv_welcome.text = welcome
            tv_address.text = address

            val city_state_zip = Phrase.from(getString(R.string.txt_city_state_zip))
                    .put("city", city)
                    .put("state", state)
                    .put("zipcode", zipcode)
                    .format()
                    .toString()

            val store_number = Phrase.from(getString(R.string.txt_store_number))
                    .put("store_number",storeID)
                    .format()
                    .toString()

            tv_store_number.text = store_number
            tv_city_state_zip.text = city_state_zip

            val lat_long = Phrase.from(getString(R.string.txt_lat_long))
                    .put("latitude", latitude.toString())
                    .put("longitude", longitude.toString())
                    .format()
                    .toString()
            tv_phone.text = phone
            tv_lat_long.text = lat_long
        }
    }
}
