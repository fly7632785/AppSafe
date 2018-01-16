package com.jafir.signprotect

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(Utils.isOwner()){
            Log.d("debug","yes")
        }else{
            Log.d("debug","no")
        }
    }
}
