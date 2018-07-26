package com.example.seemystore

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by jill
 */

val Fragment.currentActivity: FragmentActivity
    get() = this.activity ?: throw IllegalStateException("Activity must not be null")


fun AppCompatActivity.setupToolbar(activityTitle: String? = null,
                                   displayHomeButton: Boolean = false,
                                   backgroundColorId: Int? = null) {
    setSupportActionBar(toolbar)
    if (displayHomeButton) {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}

inline fun <T> SQLiteDatabase.transaction(dbFunction: SQLiteDatabase.() -> T): T {
    beginTransaction()
    val result = try {
        //based on commented fun signature
        //dbFunction(this)
        val returnVal = dbFunction()  // this.dbFunction()
        setTransactionSuccessful()
        returnVal
    } finally {
        endTransaction()
    }
    close()

    return result
}

//fun SQLiteDatabase.query(table: String, columns: Array<String>,
fun SQLiteDatabase.dbQuery(table: String, columns: Array<String>? = null,
                           selection: String? = null, selectionArgs: Array<String>? = null,
                           groupBy: String? = null, having: String? = null,
                           orderBy: String? = null): Cursor {

    return query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
}

