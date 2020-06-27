package com.consume.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    var inputType: String = "0";
    var payType: String = "0";

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        tv_Sure.setOnClickListener {
            if (input.text.toString().trim().length < 1) {
                Toast.makeText(context, "请输入金额", Toast.LENGTH_SHORT).show();
            } else {
                Log.e(
                    "xxx",
                    "intutType" + inputType + "payType" + payType + "text" + input.text.toString()
                )
                //  DatabaseHelper.query()
                val modle: ConsumeModle = ConsumeModle()
                modle.userName = DatabaseHelper.userName
                modle.note = (if (etPayType.text.toString()
                        .trim().length < 1
                ) "日常" else etPayType.text.toString().trim())
                modle.money = input.text.toString()
                modle.payType = "" + payType
                modle.createTime = ""+System.currentTimeMillis()
                modle.inputType = inputType
                DatabaseHelper.insert(modle)
                etPayType.setText("")
                input.setText("")
//                var cusor = DatabaseHelper.query()
                Toast.makeText(context, "已保存", Toast.LENGTH_SHORT).show();
                DatabaseHelper.query()
            }
        }
        rg1.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb1 -> {
                    inputType = "0"
                }
                R.id.rb2 -> {
                    inputType = "1"
                }
            }

        }
        rg2.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb3 -> {
                    payType = "0"
                }
                R.id.rb4 -> {
                    payType = "1"
                }

                R.id.rb5 -> {
                    payType = "2"
                }
            }
        }
    }
}
