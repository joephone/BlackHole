package com.transcendence.freeland.main.route

import com.transcendence.freeland.basefun.bugly.BuglyKt
import com.transcendence.freeland.ble.test.BleTest
import com.transcendence.freeland.ble.test2.BleTest3
import com.transcendence.freeland.ble.test2.NetTest
import com.transcendence.freeland.main.index.IndexBle
import com.transcendence.freeland.main.index.SettingsIndexActivity
import com.transcendence.freeland.main.index.UIIndexActivity

/**
 * @author joephone
 * @date 2023/6/5
 * @desc
 */
interface AppConstantValueKt {

    var appIndex: Array<Class<*>>
        get() = arrayOf<Class<*>>(
            SettingsIndexActivity::class.java,
            BuglyKt::class.java,
            UIIndexActivity::class.java,
            IndexBle::class.java
        )
        set(value) = TODO()


    var uiIndex: Array<Class<*>>
        get() = arrayOf<Class<*>>(
        )
        set(value) = TODO()

    var bleIndex: Array<Class<*>>
        get() = arrayOf<Class<*>>(
            BleTest::class.java,
            BleTest3::class.java,
            NetTest::class.java
        )
        set(value) = TODO()

}