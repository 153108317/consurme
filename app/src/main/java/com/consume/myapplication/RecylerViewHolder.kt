package com.consume.myapplication

import android.text.Html
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecylerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val views: SparseArray<View>? = SparseArray()
    fun getView(viewId: Int): View? {
        var view: View? = views?.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            views?.put(viewId, view)
        }
        return view
    }

    fun setOnClick(listener: View.OnClickListener) {
            itemView.setOnClickListener(listener)
    }

    fun getRootView(): View {
        return itemView
    }

    fun setTextContent(textId: Int, content: String?): TextView? {
        val tv = getView(textId) as? TextView
        if (content != null && tv != null) {
            tv.text = Html.fromHtml(content)
        }
        return tv
    }

    fun setImage(imageId: Int, imageUrl: String): ImageView? {
        val tv = getView(imageId) as ImageView
        if (imageUrl != null && tv != null) {
//            ImageUtil.showImage(imageUrl, tv)
        }
        return tv
    }

}