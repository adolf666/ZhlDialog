package com.example.adolf.zhldialog.dialog

class ZhlDialog : ZhlBaseDialog() {
    var mViewConvertListener : ((ViewHolder,ZhlBaseDialog) ->Unit)? = null

    override fun getLayoutId(): Int {
        return mLayoutId
    }

    override fun convertView(viewHolder: ViewHolder, zhlBaseDialog: ZhlBaseDialog) {
        mViewConvertListener?.invoke(viewHolder, zhlBaseDialog)
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewConvertListener = null
    }
    companion object {
        fun init() :ZhlDialog{
            return ZhlDialog()
        }
    }

}