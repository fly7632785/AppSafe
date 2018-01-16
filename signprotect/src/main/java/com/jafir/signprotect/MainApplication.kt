package com.jafir.signprotect

import android.app.Application

/**
 * Created by jafir on 2018/1/16.
 */
class MainApplication : Application() {

    companion object {
        lateinit var instance: MainApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


}