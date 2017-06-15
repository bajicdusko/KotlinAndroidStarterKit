package com.bajicdusko.kotlinstarterkit.presenter

/**
 * Created by Dusko Bajic on 09.06.17.
 * GitHub @bajicdusko
 */
interface FragmentPresenter : Presenter {

    interface View : Presenter.View {
        fun setTitle(): Unit?
    }
}