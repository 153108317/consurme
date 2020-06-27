package com.consume.myapplication

import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(var context: Context, var name: String) :
    SQLiteOpenHelper(context, "pay.db", null, 1) {
    companion object {
        var hasLogin=false
        var money=0
        var userName: String? = "111"
        val createpay = " create table " +
                " pay (id integer primary key autoincrement ," +
                " createTime date ," +
                "  inputType text ," +
                " payType text ," +
                " money text ," +
                " note text ," +
                " userName text " +
                ")"
        val createuser =
            " create table user (id integer primary key autoincrement , userName text  , password" +
                    " text , totalmoney integer )"
        var instance: DatabaseHelper? = null
        var db: SQLiteDatabase? = null
        fun getInstance(context: Activity): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context.application)
            }
            db = instance?.writableDatabase
            return instance!!
        }

        fun query(): ArrayList<ConsumeModle> {
            val list = ArrayList<ConsumeModle>()
            val cusor = db?.rawQuery(
                " select * from pay where userName =?  order by createTime ",
                arrayOf<String>("" + userName)
            )
            money=0;
            if (cusor != null) {
                while (cusor.moveToNext()) {
                    var userName = cusor.getString(cusor.getColumnIndex("userName"))
                    var note = cusor.getString(cusor.getColumnIndex("note"))
                    var money = cusor.getString(cusor.getColumnIndex("money"))
                    var payType = cusor.getString(cusor.getColumnIndex("payType"))
                    var createTime = cusor.getString(cusor.getColumnIndex("createTime"))
                    var inputType = cusor.getString(cusor.getColumnIndex("inputType"))
                    when(inputType){
                        "0"->{
                            DatabaseHelper.money+=money.toInt()
                        }
                        "1"->{
                            DatabaseHelper.money-=money.toInt()

                        }
                    }

                    list.add(ConsumeModle(createTime ,inputType,payType,money,note,userName))

                }
                cusor.close()
                Log.e(
                    "xxx","yue"+ money  )
            }
            return list
        }

        fun insert(model: ConsumeModle) {

            db?.execSQL(
                "insert into pay (createTime,inputType,payType,money,note ,userName) values(?,?,?,?,?,? )"
                ,
                arrayOf<String>(
                    "" + model.createTime,
                    "" + model.inputType,
                    "" + model.payType,
                    "" + model.money,
                    model.note!!,
                    model.userName!!
                )
            )
        }
    }

    private constructor(context: Context) : this(context, "")

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(createpay)
        p0?.execSQL(createuser)
        p0?.execSQL(
            "insert into user(userName,password) values(?,?)",
            arrayOf<String>("111", "111")
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

}