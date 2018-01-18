package com.jafir.signprotect

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.text).text = "jafir"
        findViewById<TextView>(R.id.edit).text = "edit"
        findViewById<TextView>(R.id.text).setOnClickListener {
            startActivity(Intent(this,SharedActivity::class.java))
        }
    }

    init {
        System.loadLibrary("encrypt")
    }
}
