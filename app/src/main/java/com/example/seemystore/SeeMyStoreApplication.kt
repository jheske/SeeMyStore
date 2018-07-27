/*
 * Created by Jill Heske on 7/26/2018
 * Copyright (c) All rights reserved
 */

package com.example.seemystore

import android.app.Application
import com.facebook.stetho.Stetho

class SeeMyStoreApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //Database debugging in Chrome
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build())

    }
}