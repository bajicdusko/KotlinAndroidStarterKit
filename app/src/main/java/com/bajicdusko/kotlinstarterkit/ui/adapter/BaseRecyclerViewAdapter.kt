package com.bajicdusko.kotlinstarterkit.ui.adapter

import android.support.v7.widget.RecyclerView
import com.bajicdusko.kotlinstarterkit.di.activity.ActivityComponent
import com.bajicdusko.kotlinstarterkit.di.adapter.AdapterModule

/**
 * Created by Dusko Bajic on 11.06.17.
 * GitHub @bajicdusko
 */
abstract class BaseRecyclerViewAdapter<VH : BaseViewHolder>(activityComponent: ActivityComponent) : RecyclerView.Adapter<VH>() {
    protected val injector = activityComponent.adapterBuilder().adapterModule(AdapterModule()).build()
}