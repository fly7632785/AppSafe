package com.jafir.signprotect

import android.content.pm.PackageManager
import android.util.Log
import java.security.MessageDigest


/**
 * Created by jafir on 2018/1/16.
 */
class Utils {

    companion object {
        private val APP_SIGN = "eda117e3da33ce601d3643397547ef8b"

        fun isOwner(): Boolean {
            Log.d("debug", getSignMd5())
            return getSignMd5().equals(APP_SIGN)
        }

        fun getSign(): String {
            val ctx = MainApplication.instance
            val packageinfo = ctx.packageManager.getPackageInfo(ctx.packageName, PackageManager.GET_SIGNATURES)
            val signatures = packageinfo.signatures
            val builder = StringBuilder()
            signatures.forEach {
                builder.append(it.toCharsString())
            }
            return builder.toString()
        }

        @JvmStatic
        fun getSignMd5(): String? {
            val hexDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
            try {
                val btInput = getSign().toByteArray()
                // 获得MD5摘要算法的 MessageDigest 对象
                val mdInst = MessageDigest.getInstance("MD5")
                // 使用指定的字节更新摘要
                mdInst.update(btInput)
                // 获得密文
                val md = mdInst.digest()
                // 把密文转换成十六进制的字符串形式
                val j = md.size
                val str = CharArray(j * 2)
                var k = 0
                for (i in 0 until j) {
                    val byte0 = md[i]
                    str[k++] = hexDigits[byte0.toInt().ushr(4) and 0xf]
                    str[k++] = hexDigits[byte0.toInt() and 0xf]
                }
                return String(str)
            } catch (e: Exception) {
                return null
            }

        }
    }

}