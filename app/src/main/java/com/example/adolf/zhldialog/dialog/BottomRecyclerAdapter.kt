package com.example.adolf.zhldialog.dialog

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adolf.zhldialog.R
import kotlinx.android.synthetic.main.item_bottom_dialog.view.*

class BottomRecyclerAdapter(private val mDataList :List<String>,private val itemClick :(String) ->Unit) :RecyclerView.Adapter<BottomRecyclerAdapter.RecyclerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bottom_dialog,parent,false)
        return RecyclerViewHolder(view,itemClick)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindBModel(mDataList[position])
    }


    class RecyclerViewHolder(item :View, private val itemClick: (String) -> Unit) :RecyclerView.ViewHolder(item){
        fun bindBModel(model :String){
            itemView.mItemTv.text = model
            itemView.setOnClickListener{itemClick(model)}
        }
    }

}