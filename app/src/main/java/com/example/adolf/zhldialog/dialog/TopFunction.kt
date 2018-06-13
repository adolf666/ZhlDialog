package com.example.adolf.zhldialog.dialog

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

const val WRAP_CONTENT = WindowManager.LayoutParams.WRAP_CONTENT
const val MARGIN_FIRST = 0

fun getScreenWidth(context: Context?) :Int{
    if (context == null){
        return 0
    }
        val display :DisplayMetrics =context.resources.displayMetrics
        return display.widthPixels

}

fun dp2Px(context: Context?, dipValue: Int) :Int{
    if (context == null){
        return 0
    }
    val scale :Float = context.resources.displayMetrics.density
    return (dipValue *scale +0.5f).toInt()
}