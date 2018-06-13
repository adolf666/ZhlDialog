package com.example.adolf.zhldialog.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.*
import com.example.adolf.zhldialog.R

abstract class ZhlBaseDialog :DialogFragment(){

    var mLayoutId  = 0
    var mMargin :Int = 0
    var mWidth :Int = 0
    var mHeight :Int = 0
    var mDimAmount :Float = 0.5f //灰度深浅
    var mIsShowBottom :Boolean = false
    var mCancelOutside :Boolean = true
    var mWindowAnimStyle :Int = 0

    protected abstract fun getLayoutId() :Int
    protected abstract fun convertView(viewHolder: ViewHolder,zhlBaseDialog: ZhlBaseDialog)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.ZhlDialog)
        mLayoutId = getLayoutId()

        //恢复保存的数据
        if (savedInstanceState == null) {return}
            mMargin = savedInstanceState.getInt(MARGIN)
            mWidth = savedInstanceState.getInt(WIDTH)
            mHeight = savedInstanceState.getInt(HEIGHT)
            mDimAmount = savedInstanceState.getFloat(DIM)
            mIsShowBottom = savedInstanceState.getBoolean(BOTTOM)
            mCancelOutside = savedInstanceState.getBoolean(CANCEL)
            mWindowAnimStyle = savedInstanceState.getInt(ANIM)
            mLayoutId = savedInstanceState.getInt(LAYOUT)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutId(),container,false)
        convertView(ViewHolder(view),this)
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(MARGIN, mMargin)
        outState.putInt(WIDTH, mWidth)
        outState.putInt(HEIGHT, mHeight)
        outState.putFloat(DIM, mDimAmount)
        outState.putBoolean(BOTTOM, mIsShowBottom)
        outState.putBoolean(CANCEL, mCancelOutside)
        outState.putInt(ANIM, mWindowAnimStyle)
        outState.putInt(LAYOUT, mLayoutId)
    }

    override fun onStart() {
        super.onStart()
        initDialogParams()
    }

    fun initDialogParams(){
        isCancelable = mCancelOutside
        val window :Window = dialog.window
        val lp  :WindowManager.LayoutParams = window.attributes
        lp.dimAmount = mDimAmount
        if (mIsShowBottom) lp.gravity = Gravity.BOTTOM
        when (mWidth) {
            MARGIN_FIRST -> lp.width =  getScreenWidth(context)  - 2 * dp2Px(context,mMargin)
            WRAP_CONTENT -> lp.width = WRAP_CONTENT
            else -> lp.width = dp2Px(context,mWidth)
        }
        when (mHeight){
            WRAP_CONTENT -> lp.height = WRAP_CONTENT
            else -> lp.height = dp2Px(context,mHeight)
        }
        with(window){
            setWindowAnimations(mWindowAnimStyle)
            attributes = lp
        }
    }

    fun showDialog(fragmentManager: FragmentManager?){
        show(fragmentManager, System.currentTimeMillis().toString())
    }

    companion object {
        private const val MARGIN = "margin"
        private const val WIDTH = "width"
        private const val HEIGHT = "height"
        private const val DIM = "dim_amount"
        private const val BOTTOM = "show_bottom"
        private const val CANCEL = "out_cancel"
        private const val ANIM = "anim_style"
        private const val LAYOUT = "layout_id"
    }

}