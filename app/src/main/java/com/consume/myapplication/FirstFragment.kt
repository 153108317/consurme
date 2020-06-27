package com.consume.myapplication

import android.app.ListActivity
import android.content.Intent
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
class FirstFragment : Fragment() {
    var rva: RecyclerViewAdapter<ConsumeModle>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rva = RecyclerViewAdapter<ConsumeModle>(context, R.layout.item_pddgood)
        rv.adapter = rva
        rv.layoutManager = LinearLayoutManager(context)
        rva!!.setIHolder(object : RecyclerViewAdapter.IHolder<ConsumeModle> {
            override fun conver(holder: RecylerViewHolder, t: ConsumeModle?, position: Int) {
                when (t!!.inputType) {
                    "0" -> {
                        holder.setTextContent(R.id.tv_name, "收入")!!
                            .setTextColor(resources.getColor(R.color.color_3dcc82))

                    }
                    "1" -> {
                        holder.setTextContent(R.id.tv_name, "支出")!!
                            .setTextColor(resources.getColor(R.color.color_ff2a32))
                    }
                }
                when (t!!.payType) {
                    "0" -> {
                        holder.setTextContent(R.id.tv_paytype, "支付宝")
                    }
                    "1" -> {
                        holder.setTextContent(R.id.tv_paytype, "微信")
                    }
                    "2" -> {
                        holder.setTextContent(R.id.tv_paytype, "银行卡")
                    }
                }
                holder.setTextContent(R.id.goodPrice, "消费类型： " + t!!.note)
                holder.setTextContent(R.id.createUrl, "创建时间： " + StringUtil.getDate(t!!.createTime))
            }

        })
        getData()
        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            var it = Intent(context, PayListActivity::class.java)
            startActivity(it)
        }
    }

    override fun onResume() {
        super.onResume()
        if(DatabaseHelper.hasLogin){
            tvUserName.setText(DatabaseHelper.userName)
            tvMoney.setText("余额："+DatabaseHelper.money)
        }
    }

    fun getData() {
        val list = DatabaseHelper.query()
        if (list != null) {
            rva!!.unDataList(list)
        }
    }
}
