package com.example.seemystore.database

import android.provider.BaseColumns

val DATABASE_NAME = "remotestores.db"
val DATABASE_VERSION = 1  //1.0, 1.1, etc.

/**
 * Schema for one entry in the database
 *
 * _COL_ID vars are determined at runtime in OrchidTable.kt
 * so they will be correct no matter how much their order is
 * swapped around in the CREATE TABLE statement
 */
object StoreEntry : BaseColumns {
    val STORE_TABLE_NAME = "stores"
    val ID_COL = "_id"
    var ID_COL_ID = 0
    val STORE_ID_COL = "store_id"
    var STORE_ID_COL_ID = 1
    val STORE_LOGO_URL_COL = "store_logo_url"
    var STORE_LOGO_URL_COL_ID = 2
    val NAME_COL = "name"
    var NAME_COL_ID = 3
    val ADDRESS_COL = "address"
    var ADDRESS_COL_ID = 4
    val CITY_COL = "city"
    var CITY_COL_ID = 5
    val STATE_COL = "state"
    var STATE_COL_ID = 6
    val ZIPCODE_COL = "zipcode"
    var ZIPCODE_COL_ID = 6
    val PHONE_COL = "phone"
    var PHONE_COL_ID = 7
    val LATITUDE_COL = "latitude"
    var LATITUDE_COL_ID = 8
    val LONGITUDE_COL = "longitude"
    var LONGITUDE_COL_ID = 9
}



