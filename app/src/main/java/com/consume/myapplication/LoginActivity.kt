package com.consume.myapplication

import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : BaseActivity() {
    private var username: EditText? = null
    private var userpassword: EditText? = null
    private var loginActivity: LoginActivity? = null
    private var name: String? = null



    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun init() {
        val login = findViewById<Button>(R.id.login)
        loginActivity = this

        login.setOnClickListener {
            username = findViewById<View>(R.id.account_input) as EditText
            userpassword = findViewById<View>(R.id.password_input) as EditText
            val userName = username!!.text.toString()
            val passWord = userpassword!!.text.toString()

            if (userName.equals("111")&&passWord.equals("111")) {
                DatabaseHelper.hasLogin=true
                Toast.makeText(
                    this@LoginActivity,
                    "登录成功（$userName，$passWord）",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            } else {
                Toast.makeText(this@LoginActivity, "登录失败", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(this,"请登录",Toast.LENGTH_SHORT).show()
            return false
        }
        return super.onKeyUp(keyCode, event)
    }


}