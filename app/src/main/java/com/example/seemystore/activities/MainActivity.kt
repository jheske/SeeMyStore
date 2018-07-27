/*
 * Created by Jill Heske on 7/26/2018
 * Copyright (c) All rights reserved
 */

package com.example.seemystore.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.seemystore.R
import com.example.seemystore.setToolbarTitle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_activity_toolbar)
        setToolbarTitle(getString(R.string.app_name))
    }


}
