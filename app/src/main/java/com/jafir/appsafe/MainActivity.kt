package com.jafir.appsafe

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSalt()
    }

    private fun getSalt() {
        try {

            val classzl = Class.forName("com.android.internal.widget.LockPatternUtils")
            val lockPatternUtil = classzl.getConstructor(Context::class.java).newInstance(this)
            val lockClazz = lockPatternUtil.javaClass
            val getSaltMethod = lockClazz.getDeclaredMethod("getSalt", Int::class.java)
            getSaltMethod.isAccessible = true
            val saltObj = getSaltMethod.invoke(lockPatternUtil,0)
            Log.d("debug", "$saltObj")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun passwordToHash(password: String?, userId: Int): ByteArray? {
        if (password == null) {
            return null
        }
        var algo: String? = null
        var hashed: ByteArray? = null
        try {
            val saltedPassword = (password + getSalt()).toByteArray()
            val sha1 = MessageDigest.getInstance("SHA-1").digest(saltedPassword)
            val md5 = MessageDigest.getInstance("MD5").digest(saltedPassword)
            hashed = (toHex(sha1) + toHex(md5)).toByteArray()
        } catch (e: NoSuchAlgorithmException) {
            Log.d("debug", "Failed to encode string because of missing algorithm: " + algo!!)
        }

        return hashed
    }

    private fun toHex(ary: ByteArray): String {
        val hex = "0123456789ABCDEF"
        var ret = ""
        for (i in ary.indices) {
            ret += hex[ary[i].toInt() shr 4 and 0xf]
            ret += hex[(ary[i] and 0xf).toInt()]
        }
        return ret
    }
}
