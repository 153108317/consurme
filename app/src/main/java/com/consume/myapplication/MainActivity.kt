package com.consume.myapplication

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator


import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    var tabs: ArrayList<String> = ArrayList()
    var contentList: ArrayList<Fragment> = ArrayList()
    private var recyclerViewAdapter: RecyclerViewAdapter<Fragment>? = null

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        // etPid
        DatabaseHelper.getInstance(this)
        recyclerViewAdapter = RecyclerViewAdapter(this, R.layout.item_pager)
        //   pager.setAdapter(recyclerViewAdapter)
        tabs.add("添加")
        contentList?.add(SecondFragment())
        tabs.add("我的")
        contentList?.add(FirstFragment())

        recyclerViewAdapter?.list?.clear()
        recyclerViewAdapter?.list?.addAll(contentList)
        recyclerViewAdapter?.notifyDataSetChanged()
        pager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return contentList.get(position)
            }

            override fun getItemCount(): Int {
                return contentList.size
            }
        }
        TabLayoutMediator(tab_layout, pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                // tab:当前处于选中状态的Tab对象
                // position:当前Tab所处的位置
                tab.text = tabs.get(position)
            }
        ).attach() // 不要忘记，否则没效果
       if(! DatabaseHelper.hasLogin) {
           gotoActivity(LoginActivity::class.java, false, null)
       }}


}