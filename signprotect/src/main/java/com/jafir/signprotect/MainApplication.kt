package com.jafir.signprotect

import android.app.Application
import android.util.Log

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
        if(Utils.isOwner()){
            Log.d("debug","yes")
        }else{
            Log.d("debug","no")
        }
    }
}