package com.consume.myapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {
    protected var mActivity: BaseActivity? = null
    protected var mRootView: ViewGroup? = null
    protected var ll_loading: LinearLayout? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = activity as BaseActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(getLayout(), container, false) as ViewGroup
//        mRootView?.addView(inflater.inflate(getLayout(), mRootView, false), 0)
//        ll_loading = mRootView?.findViewById(R.id.ll_loading)
//        ll_loading?.setOnClickListener {}
//        mRootView=inflater.inflate(getLayout(), container, false)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun showLoad() {
        ll_loading?.visibility = View.VISIBLE
    }

    fun hideLoad() {
        if (context != null) {
            ll_loading?.visibility = View.GONE
        }
    }

    abstract fun getLayout(): Int
    abstract fun init()
}