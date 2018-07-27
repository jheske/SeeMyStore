/*
 * Created by Jill Heske on 7/26/2018
 * Copyright (c) All rights reserved
 */

package com.example.seemystore.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.example.seemystore.Store
import com.example.seemystore.database.StoreEntry.ADDRESS_COL
import com.example.seemystore.database.StoreEntry.CITY_COL
import com.example.seemystore.database.StoreEntry.ID_COL
import com.example.seemystore.database.StoreEntry.LATITUDE_COL
import com.example.seemystore.database.StoreEntry.LONGITUDE_COL
import com.example.seemystore.database.StoreEntry.NAME_COL
import com.example.seemystore.database.StoreEntry.PHONE_COL
import com.example.seemystore.database.StoreEntry.STATE_COL
import com.example.seemystore.database.StoreEntry.STORE_ID_COL
import com.example.seemystore.database.StoreEntry.STORE_ID_COL_ID
import com.example.seemystore.database.StoreEntry.STORE_LOGO_URL_COL
import com.example.seemystore.database.StoreEntry.STORE_LOGO_URL_COL_ID
import com.example.seemystore.database.StoreEntry.STORE_TABLE_NAME
import com.example.seemystore.database.StoreEntry.ZIPCODE_COL
import com.example.seemystore.dbQuery
import com.example.seemystore.transaction

class StoreTable(context: Context) {
    private val TAG = StoreTable::class.java.simpleName
    private val dbHelper = StoreDb(context)

    fun dbGetAllStores(): MutableList<Store> {
        val db = dbHelper.readableDatabase
        val order = "${ID_COL} ASC"
        val cursor = db.dbQuery(STORE_TABLE_NAME, orderBy = order)
        Log.d(TAG, "[dbGetAllStores] Database contains ${cursor.count} rows")
        val stores = parseStoresFrom(cursor)
        cursor.close()
        return stores
    }

    fun dbGetStore(storeId: String?): Store? {
        val db = dbHelper.readableDatabase

        if (storeId == null) {
            return null
        }
        val where = "store_id=?"
        val whereParams: Array<String> = arrayOf(storeId)
        val cursor = db.dbQuery(STORE_TABLE_NAME,
                selection = where, selectionArgs = whereParams)
        if (cursor.count <= 0)
            return null
        cursor.moveToFirst()
        val store = getStore(cursor)
        cursor.close()
        return store
    }

    fun dbAddAllStores(stores: MutableList<Store>) {
        for (store: Store in stores) {
            dbInsertStore(store)
        }
    }

    fun dbInsertStore(store: Store): Long {
        val db = dbHelper.writableDatabase

        //See ExtensionFuncs.SQLiteDatabase.transaction for all the ugly code
        val id = db.transaction {
            insert(STORE_TABLE_NAME, null, getContentValues(store))
        }
        Log.d(TAG, "[dbInsertStore] Inserted #${id} $store into the DB ")
        return id
    }

    private fun getContentValues(store: Store): ContentValues {
        val contentValues = ContentValues()

        store.apply {
            contentValues.put(STORE_LOGO_URL_COL, storeLogoURL)
            contentValues.put(STORE_ID_COL, storeID)
            contentValues.put(NAME_COL, name)
            contentValues.put(ADDRESS_COL, address)
            contentValues.put(CITY_COL, city)
            contentValues.put(STATE_COL, state)
            contentValues.put(ZIPCODE_COL, zipcode)
            contentValues.put(PHONE_COL, phone)
            contentValues.put(LATITUDE_COL, latitude)
            contentValues.put(LONGITUDE_COL, longitude)
        }
        return contentValues
    }

    private fun parseStoresFrom(cursor: Cursor): MutableList<Store> {
        val storesList = mutableListOf<Store>()

        if (cursor.count <= 0) {
            return storesList
        }
        while (cursor.moveToNext()) {
            cursor.apply {
                val store = getStore(cursor)
                store?.let {
                    storesList.add(it)
                }
            }
        }
        return storesList
    }

    fun getStore(cursor: Cursor): Store? {
        var store: Store? = null

        cursor.apply {
            val storeLogoUrl = getString(STORE_LOGO_URL_COL_ID)
            val storeId = getString(STORE_ID_COL_ID)
            val name = getString(StoreEntry.NAME_COL_ID)
            val address = getString(StoreEntry.ADDRESS_COL_ID)
            val city = getString(StoreEntry.CITY_COL_ID)
            val state = getString(StoreEntry.STATE_COL_ID)
            val zipcode = getString(StoreEntry.ZIPCODE_COL_ID)
            val phone = getString(StoreEntry.PHONE_COL_ID)
            val latitude = getDouble(StoreEntry.LATITUDE_COL_ID)
            val longitude = getDouble(StoreEntry.LONGITUDE_COL_ID)
            store = Store(storeLogoUrl, storeId, name, address,
                    city, state, zipcode, phone, latitude, longitude)
        }
        return store
    }
}


