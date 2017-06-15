package com.bajicdusko.kotlinstarterkit.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife

/**
 * Created by Dusko Bajic on 11.06.17.
 * GitHub @bajicdusko
 */
abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    init {
        ButterKnife.bind(this, view)
    }
}