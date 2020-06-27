package com.consume.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter<T> : RecyclerView.Adapter<RecylerViewHolder> {
    var list: ArrayList<T> = ArrayList()
    private var iHolder: IHolder<T>? = null;//object : IHolder<T>
    private var layoutId: Int = 0
    private var context: Context? = null
    fun unDataList(arryList: ArrayList<T>) {
        if (arryList != null) {
            list.clear()
            list.addAll(arryList)
            notifyDataChanged()
        }

    }


    constructor(context: Context?, layoutId: Int) {
        this.layoutId = layoutId
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecylerViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return RecylerViewHolder(view)
    }

    private var lastTime: Long = 0L
    override fun onBindViewHolder(holder: RecylerViewHolder, position: Int) {
        iHolder?.conver(holder, list?.get(position), position)
        if (position > 30 && position + 4 >= itemCount) {
            if (loadLister != null) {
                val now = System.currentTimeMillis()
                if (now - lastTime > 1000) {
                    loadLister?.addMore()
                    lastTime = now
                }
            }
        }
    }

    private var loadLister: LoadLister? = null
    fun setLoadLister(loadLister: LoadLister) {
        this.loadLister = loadLister
    }

    interface LoadLister {
        public fun addMore()
    }

    fun setIHolder(holder: IHolder<T>) {
        iHolder = holder
    }

    interface IHolder<T> {
        fun conver(holder: RecylerViewHolder, t: T?, position: Int)
    }

    fun notifyDataChanged() {
        super.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (list != null) {
            return list.size
        }
        return 0
    }

    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }
}