package com.example.seemystore

import android.app.Application
import com.facebook.stetho.Stetho

class SeeMyStoreApplication : Application() {
    private var mNetworkAvailable = false


    override fun onCreate() {
        super.onCreate()

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build())

    }
}