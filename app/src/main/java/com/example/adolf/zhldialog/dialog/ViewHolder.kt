package com.example.adolf.zhldialog.dialog

import android.util.SparseArray
import android.view.View
import android.widget.TextView

class ViewHolder(val convertView: View){
    private var views :SparseArray<View> = SparseArray()

    fun <T : View> getView(viewId: Int): T {
        var view: View? = views.get(viewId)
        if (view == null) {
            view = convertView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as T
    }

    fun getConventView() :View{
        return convertView
    }

    fun setText(viewId :Int,str :String){
        val textView :TextView = getView(viewId)
        textView.text = str
    }

    fun setOnClickListener(viewId :Int,listener :View.OnClickListener){
        val view :View = getView(viewId)
        view.setOnClickListener(listener)
    }



    companion object {
        fun create(view: View) :ViewHolder{
            return ViewHolder(view)
        }
    }
}