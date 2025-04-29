package com.transcendence.greenstar.main.index

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.transcendence.aop.permission.Permissions
import com.transcendence.core.base.activity.AppAc
import com.transcendence.core.permission.PermissionPool
import com.transcendence.core.utils.StringUtils
import com.transcendence.greenstar.R

/**
 * @author joephone
 * @date 2023/5/4
 * @desc  popupwindow
 */
class PopupIndexKt : AppAc(), AdapterView.OnItemClickListener {

    private lateinit var lvIndex: ListView
    private var adapter: ArrayAdapter<String>? = null

    override fun getLayoutId(): Int {

        return R.layout.activity_index
    }

    override fun initView() {
        lvIndex = findViewById(R.id.lvIndex)

        val items = StringUtils.getStringListAndIndex(mActivity, R.array.popup_index_item)
        adapter = ArrayAdapter(
            mActivity,
            android.R.layout.simple_list_item_1, items
        )
        lvIndex.setAdapter(adapter)
        lvIndex.setOnItemClickListener(this)
    }

    @Permissions(PermissionPool.ACCESS_FINE_LOCATION, PermissionPool.ACCESS_COARSE_LOCATION)
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent = Intent()
        intent.setClass(mActivity, popupIndex.get(position))
        startActivity(intent)
    }

    var popupIndex = arrayOf<Class<*>>(
//        GSPDBAc::class.java,
    )
}