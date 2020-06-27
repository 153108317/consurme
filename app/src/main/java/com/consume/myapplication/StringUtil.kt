package com.consume.myapplication

import android.text.TextUtils
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class StringUtil {
    companion object {
        fun getDate(long: String?): String {
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            Long
            val d1 = Date(long!!.toLong())
            val t1: String = format.format(d1)
            return t1
        }
//        date2String(new Date(startTime), DateUtil.YMD_HM_FORMAT)
    }
}