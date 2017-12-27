package com.bajicdusko.kotlinstarterkit.ui.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

/**
 * Created by Dusko Bajic on 12.06.17.
 * GitHub @bajicdusko
 */
class DefaultRecyclerView(context: Context, attributeSet: AttributeSet?, defStyle: Int) : RecyclerView(context,
    attributeSet, defStyle) {

  constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
  constructor(context: Context) : this(context, null)

  init {
    layoutManager = LinearLayoutManager(context)
    addItemDecoration(SimpleDividerItemDecoration(context))
  }
}