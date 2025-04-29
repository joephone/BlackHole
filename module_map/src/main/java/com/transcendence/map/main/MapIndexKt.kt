package com.transcendence.map.main

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.alibaba.android.arouter.facade.annotation.Route
import com.transcendence.aop.permission.PermissionPool
import com.transcendence.aop.permission.Permissions
import com.transcendence.core.base.activity.AppAc
import com.transcendence.core.base.route.RoutePath
import com.transcendence.core.utils.StringUtils
import com.transcendence.map.R
import com.transcendence.map.gps.GSPDBAc

/**
 * @author joephone
 * @date 2023/3/23
 * @desc
 */
@Route(path = RoutePath.Map.PAGER_MAIN)
class MapIndexKt : AppAc(), AdapterView.OnItemClickListener {

    private var adapter: ArrayAdapter<String>? = null
    private val items: List<String>? = null
    private lateinit var lvIndex: ListView

    override fun getLayoutId(): Int {
        return R.layout.activity_index
    }

    override fun initView() {
        lvIndex = findViewById(R.id.lvIndex)

        val items = StringUtils.getStringListAndIndex(mActivity, R.array.map_index_item)
        adapter = ArrayAdapter(
            mActivity,
            android.R.layout.simple_list_item_1, items
        )
        lvIndex.setAdapter(adapter)
        lvIndex.setOnItemClickListener(this)
    }

    @Permissions(PermissionPool.ACCESS_FINE_LOCATION,PermissionPool.ACCESS_COARSE_LOCATION)
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent = Intent()
//        intent.setClass(mActivity, mapIndex.get(position))
//        startActivity(intent)
    }


    var mapIndex = arrayOf<Class<*>>(
        GSPDBAc::class.java,
    )
}