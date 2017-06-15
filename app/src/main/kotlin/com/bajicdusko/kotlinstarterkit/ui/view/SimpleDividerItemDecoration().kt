package com.bajicdusko.kotlinstarterkit.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.annotation.DimenRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import com.bajicdusko.kotlinstarterkit.R


/**
 * Created by Dusko Bajic on 12.06.17.
 * GitHub @bajicdusko
 */
class SimpleDividerItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {

    var mDivider: Drawable
    var paddingLeftRight: Int = 0

    init {
        mDivider = ContextCompat.getDrawable(context, R.drawable.line_divider)
    }

    constructor(context: Context, @DrawableRes dividerDrawable: Int, @DimenRes paddingLeftRight: Int) : this(context) {
        mDivider = ContextCompat.getDrawable(context, dividerDrawable)
        this.paddingLeftRight = context.resources.getDimension(paddingLeftRight) as Int
    }

    override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        val left: Int
        val right: Int
        var width: Int = 0
        if (parent != null) {
            width = parent.width
        } else {
            width = context.resources.displayMetrics.widthPixels
        }
        if (paddingLeftRight == 0) {
            left = parent?.paddingLeft ?: 0
            right = (parent?.width ?: 0) - (parent?.paddingRight ?: 0)
        } else {
            left = paddingLeftRight
            right = width - paddingLeftRight
        }

        val childCount = parent?.childCount ?: 0
        for (i in 0..childCount - 1) {
            val child = parent?.getChildAt(i)

            var params: RecyclerView.LayoutParams?
            if (child != null) {
                params = child.layoutParams as RecyclerView.LayoutParams
                val top = child.bottom + params.bottomMargin
                val bottom = top + mDivider.intrinsicHeight

                mDivider.setBounds(left, top, right, bottom)
                mDivider.draw(c)
            }
        }
    }

}