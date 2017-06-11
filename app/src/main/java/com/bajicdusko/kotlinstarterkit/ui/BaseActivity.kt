package com.bajicdusko.kotlinstarterkit.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import com.bajicdusko.kotlinstarterkit.MyApplication

/**
 * Created by Dusko Bajic on 28.05.17.
 * GitHub @bajicdusko
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun inject()
    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        inject()
        ButterKnife.bind(this)
    }

    val injector by lazy { (application as MyApplication).activityComponent(this) }
}