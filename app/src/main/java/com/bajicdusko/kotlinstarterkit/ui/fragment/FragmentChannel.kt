package com.bajicdusko.kotlinstarterkit.ui.fragment

import android.support.annotation.StringRes

/**
 * Created by Dusko Bajic on 07.06.17.
 * GitHub @bajicdusko
 */
interface FragmentChannel {
    fun setTitle(@StringRes titleId: Int)
    fun showHome()
}