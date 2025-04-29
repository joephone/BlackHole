package com.transcendence.greenstar.main.index

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.transcendence.core.base.activity.AppAc
import com.transcendence.core.utils.StringUtils
import com.transcendence.greenstar.R
import com.transcendence.greenstar.base.route.GreenStarConstantValue

/**
 * @author joephone
 * @date 2023/3/23
 * @desc
 */
class DemoIndexKt : AppAc(), AdapterView.OnItemClickListener {

    private lateinit var lvIndex: ListView
    private var adapter: ArrayAdapter<String>? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_index
    }

    override fun initView() {
        setTitle("其他序列")
        lvIndex = findViewById<ListView>(R.id.lvIndex)

        val items = StringUtils.getStringListAndIndex(mActivity, R.array.demo_index_item)
        adapter = ArrayAdapter(
            mActivity,
            android.R.layout.simple_list_item_1, items
        )
        lvIndex.setAdapter(adapter)
        lvIndex.setOnItemClickListener(this)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent = Intent()
        intent.setClass(mActivity, GreenStarConstantValue.demoIndex[position])
        startActivity(intent)
    }
}