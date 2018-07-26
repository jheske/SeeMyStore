package com.example.seemystore.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.seemystore.BuildConfig.DEBUG
import com.example.seemystore.database.StoreEntry.ADDRESS_COL
import com.example.seemystore.database.StoreEntry.CITY_COL
import com.example.seemystore.database.StoreEntry.LATITUDE_COL
import com.example.seemystore.database.StoreEntry.LONGITUDE_COL
import com.example.seemystore.database.StoreEntry.NAME_COL
import com.example.seemystore.database.StoreEntry.PHONE_COL
import com.example.seemystore.database.StoreEntry.STATE_COL
import com.example.seemystore.database.StoreEntry.STORE_ID_COL
import com.example.seemystore.database.StoreEntry.STORE_LOGO_URL_COL
import com.example.seemystore.database.StoreEntry.STORE_TABLE_NAME
import com.example.seemystore.database.StoreEntry.ZIPCODE_COL

class StoreDb(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,
        null, DATABASE_VERSION) {

    private val TAG = StoreDb::class.java.simpleName

    private val SQL_CREATE_STORES_TABLE = "CREATE TABLE IF NOT EXISTS $STORE_TABLE_NAME (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "${STORE_ID_COL} TEXT, " +
            "${STORE_LOGO_URL_COL} TEXT, " +
            "${NAME_COL} TEXT UNIQUE NOT NULL, " +
            "${ADDRESS_COL} TEXT,  " +
            "${CITY_COL} TEXT, " +
            "${STATE_COL} TEXT, " +
            "${ZIPCODE_COL} TEXT, " +
            "${PHONE_COL} TEXT, " +
            "${LATITUDE_COL} REAL, " +
            "${LONGITUDE_COL} REAL" +
            ")"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_STORES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (DEBUG)
            Log.d(TAG, "Upgrading database from version $oldVersion to $newVersion")
        if (newVersion > oldVersion) {
            //TODO WATCH OUT, ALL DATA WILL BE LOST ON onCreate(db)
            //onCreate(db)
        }
        //onCreate(db)
    }
}