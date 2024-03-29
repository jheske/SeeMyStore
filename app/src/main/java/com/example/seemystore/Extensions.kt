/*
 * Created by Jill Heske on 7/26/2018
 * Copyright (c) All rights reserved
 */

package com.example.seemystore

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*
import android.content.Context
import android.net.ConnectivityManager


/**
 * Created by jill
 */


val Fragment.currentActivity: FragmentActivity
    get() = this.activity ?: throw IllegalStateException("Activity must not be null")


fun Context.isNetworkAvailable(): Boolean {
    var haveConnectedWifi = false
    var haveConnectedMobile = false
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
    val netInfo = cm.allNetworkInfo
    for (ni in netInfo) {
        if (ni.typeName.equals("WIFI", ignoreCase = true))
            if (ni.isConnected)
                haveConnectedWifi = true
        if (ni.typeName.equals("MOBILE", ignoreCase = true))
            if (ni.isConnected)
                haveConnectedMobile = true
    }
    return haveConnectedWifi || haveConnectedMobile
}

fun AppCompatActivity.setToolbarTitle(activityTitle: String?) {
    supportActionBar?.title = activityTitle.let {
        it
    }
}

fun AppCompatActivity.setupToolbar(activityTitle: String? = null,
                                   displayHomeButton: Boolean = false,
                                   backgroundColorId: Int? = null) {
    setSupportActionBar(toolbar)
    if (displayHomeButton) {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    setToolbarTitle(activityTitle)
    backgroundColorId?.let {
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat
                .getColor(this,
                        it)))
    }
}

inline fun <T> SQLiteDatabase.transaction(dbFunction: SQLiteDatabase.() -> T): T {
    beginTransaction()
    val result = try {
        val returnVal = dbFunction()
        setTransactionSuccessful()
        returnVal
    } finally {
        endTransaction()
    }
    close()

    return result
}

fun SQLiteDatabase.dbQuery(table: String, columns: Array<String>? = null,
                           selection: String? = null, selectionArgs: Array<String>? = null,
                           groupBy: String? = null, having: String? = null,
                           orderBy: String? = null): Cursor {

    return query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
}

