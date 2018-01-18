package com.jafir.signprotect

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar


/**
 * Created by jafir on 2018/1/18.
 *
 *
 * 当被别人通过action隐式打开的时候
 * 想返回自己app的主界面
 * 1、重写toolbar返回和Activity返回  main为singleTask 跳转主界面    推荐第一种
 * 2、利用ActivityParent的方式，但是这个方式只针对于 toolbar的导航键返回起作用
 *
 */
class SharedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shared)
        /**
         * 第一种利用ActivityParent
         */
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        // 启用向上导航按钮
//        if (NavUtils.getParentActivityName(this) != null) {
//            supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        }
        /**
         * 第二种重写back intent开启main
         */
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }

    override fun onBackPressed() {
        /**
         * 第一种
         */
//        val upIntent = NavUtils.getParentActivityIntent(this)
//        if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
//            TaskStackBuilder.create(this)
//                    .addNextIntentWithParentStack(upIntent)
//                    .startActivities()
//        } else {
//            NavUtils.navigateUpTo(this, upIntent)
//        }
        /**
         * 第二种
         */
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}