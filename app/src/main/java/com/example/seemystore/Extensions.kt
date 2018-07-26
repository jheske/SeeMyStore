package com.example.seemystore

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import retrofit2.http.Url
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import android.R.attr.bitmap


/**
 * Created by jill
 */

val Fragment.currentActivity: FragmentActivity
    get() = this.activity ?: throw IllegalStateException("Activity must not be null")


fun AppCompatActivity.setToolbarTitle(activityTitle: String?) {
    supportActionBar?.title = activityTitle.let {
        it
    }
}

fun AppCompatActivity.setToolbarLogo(urlString: String?) {
    urlString?.let {
        val logo = getLogoDrawable(it)
        supportActionBar?.setLogo(logo)
    }
}

fun AppCompatActivity.setupToolbar(displayHomeButton: Boolean = false,
                                   backgroundColorId: Int? = null) {
    setSupportActionBar(toolbar)
    if (displayHomeButton) {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    backgroundColorId?.let {
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat
                .getColor(this,
                        it)))
    }
}

fun getLogoDrawable(urlString: String): Drawable? {
    val inputStream = java.net.URL(urlString).openStream()
    return BitmapDrawable(inputStream)
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

