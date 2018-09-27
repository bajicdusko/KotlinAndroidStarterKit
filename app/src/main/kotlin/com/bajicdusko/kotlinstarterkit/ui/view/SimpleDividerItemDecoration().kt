package com.bajicdusko.kotlinstarterkit.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.annotation.DimenRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bajicdusko.kotlinstarterkit.R


/**
 * Created by Dusko Bajic on 12.06.17.
 * GitHub @bajicdusko
 */
class SimpleDividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

  private var mDivider: Drawable?
  private var paddingLeftRight: Int = 0

  init {
    mDivider = ContextCompat.getDrawable(context, R.drawable.line_divider)
  }

  constructor(context: Context, @DrawableRes dividerDrawable: Int, @DimenRes
  paddingLeftRight: Int) : this(context) {
    mDivider = ContextCompat.getDrawable(context, dividerDrawable)
    this.paddingLeftRight = context.resources.getDimension(paddingLeftRight).toInt()
  }

  override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
    val left: Int
    val right: Int
    val width = parent.width

    if (paddingLeftRight == 0) {
      left = parent.paddingLeft
      right = parent.width - parent.paddingRight
    } else {
      left = paddingLeftRight
      right = width - paddingLeftRight
    }

    val childCount = parent.childCount
    for (i in 0 until childCount) {
      val child: View = parent.getChildAt(i)

      var params: RecyclerView.LayoutParams
      params = child.layoutParams as RecyclerView.LayoutParams
      val top = child.bottom + params.bottomMargin
      val bottom = top + (mDivider?.intrinsicHeight ?: 0)

      mDivider?.setBounds(left, top, right, bottom)
      mDivider?.draw(c)
    }
  }
}