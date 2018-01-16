package com.jafir.signprotect

import android.content.pm.PackageManager

/**
 * Created by jafir on 2018/1/16.
 */
class Utils {

    companion object {
        private val APP_SIGN = "fly7632785"

        fun isOwner(): Boolean {
            return getSign().equals(APP_SIGN)
        }

        fun getSign():String{
            val ctx = MainApplication.instance
            val packageinfo = ctx.packageManager.getPackageInfo(ctx.packageName,PackageManager.GET_SIGNATURES)
            val signatures = packageinfo.signatures
            val builder = StringBuilder()
            signatures.forEach {
                builder.append(it.toCharsString())
            }
            return builder.toString()
        }
    }

}