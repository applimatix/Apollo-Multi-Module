package com.example.apollomultimodule.util

import android.R
import android.util.TypedValue
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter


@BindingAdapter("android:onClick")
fun setBackgroundForOnClick(view: ConstraintLayout, listener: View.OnClickListener? ) {

    if (listener != null) {
        val outValue = TypedValue()
        view.context.theme.resolveAttribute(R.attr.selectableItemBackground, outValue, true)
        view.setBackgroundResource(outValue.resourceId)
    }
    view.setOnClickListener(listener)
}
