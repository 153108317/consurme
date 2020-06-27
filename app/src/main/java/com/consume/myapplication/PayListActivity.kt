package com.consume.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PayListActivity :  BaseActivity() {
    var rva: RecyclerViewAdapter<ConsumeModle>? = null



    fun getData(){
      val list=  DatabaseHelper.query()
        if(list!=null){
            rva!!.unDataList(list)
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_paylist
    }

    override fun init() {
        button_first.setVisibility(View.GONE)
        rva = RecyclerViewAdapter<ConsumeModle>(this, R.layout.item_pddgood)
        rv.adapter = rva
        rv.layoutManager=LinearLayoutManager(this)
        rva!!.setIHolder(object :RecyclerViewAdapter.IHolder<ConsumeModle>{
            override fun conver(holder: RecylerViewHolder, t: ConsumeModle?, position: Int) {
                when(t!!.inputType){
                    "0"->{
                        holder.setTextContent(R.id.tv_name,"收入:"+t.money)!!.setTextColor(resources.getColor(R.color.color_3dcc82))

                    }
                    "1"->{
                        holder.setTextContent(R.id.tv_name,"支出:"+t.money)!!.setTextColor(resources.getColor(R.color.color_ff2a32))
                    }
                }
                when(t!!.payType){
                    "0"->{
                        holder.setTextContent(R.id.tv_paytype,"支付宝")
                    }
                    "1"->{
                        holder.setTextContent(R.id.tv_paytype,"微信")
                    }
                    "2"->{
                        holder.setTextContent(R.id.tv_paytype,"银行卡")
                    }
                }
                holder.setTextContent(R.id.goodPrice,"消费类型： "+t!!.note)
                holder.setTextContent(R.id.createUrl, "创建时间： " + StringUtil.getDate(t!!.createTime))

            }

        })
        getData()
//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
////            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }
}
