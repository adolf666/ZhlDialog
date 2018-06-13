package com.example.adolf.zhldialog.dialog

import android.view.View
import android.widget.TextView
import com.example.adolf.zhldialog.R

class ZhlCommonDialog :ZhlBaseDialog(){
    var mTitleStr :String = "";
    var mMessageStr :String = ""
    var mConfirmStr :String = "确定"
    var mCancelStr :String = "取消"
    var mConfirmListener : View.OnClickListener? = null

    override fun getLayoutId(): Int {
        return R.layout.confirm_layout
    }

    override fun convertView(viewHolder: ViewHolder, zhlBaseDialog: ZhlBaseDialog) {
        val title :TextView = viewHolder.getView(R.id.title)
        val message :TextView = viewHolder.getView(R.id.message)
        val confirmBtn :TextView = viewHolder.getView(R.id.ok)
        val cancelBtn :TextView = viewHolder.getView(R.id.cancel)
        title.text = mTitleStr
        message.text = mMessageStr
        confirmBtn.text = mConfirmStr
        cancelBtn.text = mCancelStr
        cancelBtn.setOnClickListener { zhlBaseDialog.dismiss() }
        confirmBtn.setOnClickListener(mConfirmListener)
        setParams()
    }

    private fun setParams(){
        mMargin = 40
        mHeight = WRAP_CONTENT
        mCancelOutside = true
    }

    companion object {
        fun init() :ZhlCommonDialog{
            return ZhlCommonDialog()
        }
    }
}