package com.example.adolf.zhldialog

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View.OnClickListener
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.adolf.zhldialog.dialog.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mBottomList = arrayListOf<String>("设置备注及标签","标记为星标好友","设置朋友圈权限","发送名片","投诉","加入黑名单","删除","添加到桌面","更多")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mCommonDialog.setOnClickListener { showCommonDialog() }
        mBottomDialog.setOnClickListener { showBottomDialog() }
        mImageDialog.setOnClickListener { showImageDialog() }
        mCommentDialog.setOnClickListener { showCommentDialog() }
    }

    private fun showCommonDialog(){
        with(ZhlCommonDialog.init()){
            mTitleStr = "标题"
            mMessageStr = "Are you sure to delete?"
            mConfirmListener = OnClickListener { Toast.makeText(context,"EFAEF",Toast.LENGTH_SHORT).show();dismiss() }
            showDialog(supportFragmentManager)
        }
    }

    private fun showBottomDialog(){
        with(ZhlDialog.init()){
            mLayoutId = R.layout.recyclerview_layout
            mHeight = 200
            mIsShowBottom = true
            mWindowAnimStyle = R.style.PullPushAnim
            mViewConvertListener =  {viewHolder, zhlBaseDialog -> initBottomDialogUI(viewHolder,zhlBaseDialog) }
            showDialog(supportFragmentManager)
        }
    }

    private fun showImageDialog(){
        with(ZhlDialog.init()){
            mLayoutId = R.layout.image_only_layout
            mWidth = 266
            mHeight = 384
            mViewConvertListener = {viewHolder, zhlBaseDialog -> initImageOnlyDialog(viewHolder,zhlBaseDialog)}
            showDialog(supportFragmentManager)
        }
    }

    fun showCommentDialog(){
        with(ZhlDialog.init()){
            mLayoutId = R.layout.comment_layout
            mWidth = MARGIN_FIRST
            mHeight = WRAP_CONTENT
            mIsShowBottom = true
            mViewConvertListener = {viewHolder, zhlBaseDialog ->  initCommentDialog(viewHolder,zhlBaseDialog)}
            showDialog(supportFragmentManager)
        }
    }

    private fun initBottomDialogUI(viewHolder: ViewHolder,zhlBaseDialog: ZhlBaseDialog){
        val recyclerView = viewHolder.getView<RecyclerView>(R.id.mRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BottomRecyclerAdapter(mBottomList){Toast.makeText(this,it,Toast.LENGTH_SHORT).show();zhlBaseDialog.dismiss()}
        recyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
    }

    private fun initImageOnlyDialog(viewHolder: ViewHolder,zhlBaseDialog: ZhlBaseDialog){
        val imageView = viewHolder.getView<ImageView>(R.id.imageView)
        imageView.setOnClickListener { zhlBaseDialog.dismiss() }
    }

    private fun initCommentDialog(viewHolder: ViewHolder,zhlBaseDialog: ZhlBaseDialog){
        val mCommentTv = viewHolder.getView<TextView>(R.id.mCommentTv)
        val mEditTv = viewHolder.getView<EditText>(R.id.edit_input)
        mCommentTv.setOnClickListener { Toast.makeText(this,"评论成功",Toast.LENGTH_SHORT).show() ;zhlBaseDialog.dismiss() }
        mEditTv.post {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(mEditTv, 0) }
    }
}
