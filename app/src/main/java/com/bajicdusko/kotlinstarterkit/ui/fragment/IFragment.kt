package com.bajicdusko.kotlinstarterkit.ui.fragment

/**
 * Created by Dusko Bajic on 07.06.17.
 * GitHub @bajicdusko
 */
interface IFragment {
    fun getFragmentName(): String

    fun setTitle(): Unit?

    fun dispose()
}