package com.consume.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import android.widget.RelativeLayout
import androidx.annotation.Nullable


abstract class BaseActivity : AppCompatActivity() {
    var mBaseActivity: BaseActivity? = null
    protected var ll_loading: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBaseActivity = this
        this.setContentView(getLayout())

    }

    override fun setContentView(layoutId: Int) {
        super.setContentView(layoutId)
//        val parentView: RelativeLayout? = findViewById(R.id.base_rootview)
//        val contentView = layoutInflater?.inflate(layoutId, parentView, false)
//        parentView?.addView(contentView, 0)
//        ll_loading = findViewById(R.id.ll_loading)
        init()
//        ll_loading?.setOnClickListener {}
    }

    abstract fun getLayout(): Int
    abstract fun init()
    fun showLoad() {
        ll_loading?.visibility = View.VISIBLE
    }

    fun hideLoad() {
        ll_loading?.visibility = View.GONE
    }

    fun gotoActivity(cls: Class<out Activity>, finish: Boolean, bundle: Bundle?, requestCode: Int) {
        val intent = Intent(this, cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
        if (finish) {
            finish()
        }
    }

    fun gotoActivity(cls: Class<out Activity>, finish: Boolean, bundle: Bundle?) {
        val intent = Intent(this, cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
        if (finish) {
            finish()
        }
    }

    fun gotoActivity(cls: Class<out Activity>, finish: Boolean) {
        val it = Intent(this, cls)
        startActivity(it)
        if (finish) {
            finish()
        }
    }


    override fun startActivityForResult(intent: Intent, requestCode: Int, options: Bundle?) {
//        if (isCheckActivityJump() && DoubleClickUtil.isOnDoubleClick(DoubleClickUtil.defultTime)) {
//            return
//        }
        super.startActivityForResult(intent, requestCode, options)
//        DoubleClickUtil.defultTime = 800
    }

    protected fun isCheckActivityJump(): Boolean {
        return true
    }

}
