package com.transcendence.core.utils.mmkv

import android.content.Context
import android.os.Parcelable
import com.tencent.mmkv.MMKV

/**
 * @author joephone
 * @date 2023/5/15
 * @desc
 */
class MMkvHelper private constructor() {

    private var mv: MMKV? = MMKV.defaultMMKV()

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    fun encode(key: String?, `object`: Any) {
        if (`object` is String) {
            mv?.encode(key, `object`)
        } else if (`object` is Int) {
            mv?.encode(key, `object`)
        } else if (`object` is Boolean) {
            mv?.encode(key, `object`)
        } else if (`object` is Float) {
            mv?.encode(key, `object`)
        } else if (`object` is Long) {
            mv?.encode(key, `object`)
        } else if (`object` is Double) {
            mv?.encode(key, `object`)
        } else if (`object` is ByteArray) {
            mv?.encode(key, `object`)
        } else {
            mv?.encode(key, `object`.toString())
        }
    }

    fun encodeSet(key: String?, sets: Set<String?>?) {
        mv?.encode(key, sets)
    }

    fun encodeParcelable(key: String?, obj: Parcelable?) {
        mv?.encode(key, obj)
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     */
    fun decodeInt(key: String?): Int? {
        return mv?.decodeInt(key, 0)
    }

    fun decodeDouble(key: String?): Double {
        return mv?.decodeDouble(key, 0.00)!!
    }

    fun decodeLong(key: String?): Long {
        return mv?.decodeLong(key, 0L)!!
    }

    fun decodeBoolean(key: String?): Boolean {
        return mv?.decodeBool(key, false) == true
    }

    fun decodeBooleanFirstStart(key: String?): Boolean {
        return mv?.decodeBool(key, true) == true
    }

    fun decodeBooleanTure(key: String?,defaultValue: Boolean): Boolean {
        return mv?.decodeBool(key, defaultValue) == true
    }

    fun decodeFloat(key: String?): Float {
        return mv?.decodeFloat(key, 0f)!!
    }

    fun decodeBytes(key: String?): ByteArray {
        return mv?.decodeBytes(key)!!
    }

    fun decodeString(key: String?): String {
        return mv?.decodeString(key, "").toString()
    }
    fun decodeStringDef(key: String?,defaultValue: String): String {
        return mv?.decodeString(key, defaultValue).toString()
    }


    fun decodeStringSet(key: String?): Set<String> {
        return mv?.decodeStringSet(key, emptySet()) as Set<String>
    }

    fun <T : Parcelable?> decodeParcelable(key: String?, tClass: Class<T>?): T? {
        return mv?.decodeParcelable(key, tClass)
    }

    /**
     * 移除某个key对
     *
     * @param key
     */
    fun removeKey(key: String?) {
        mv?.removeValueForKey(key)
    }

    /**
     * 清除所有key
     */
    fun clearAll() {
        mv?.clearAll()
    }
    /**
     * 从sp中迁移到mmvk
     */
    fun testImportSharedPreferences(context: Context){
        val oldMan = context.getSharedPreferences("yxsSp", Context.MODE_PRIVATE)
        // 迁移旧数据
        mv?.importFromSharedPreferences(oldMan)
        // 清空旧数据
        oldMan.edit().clear().commit()
    }



    companion object {
        private var mInstance: MMkvHelper? = null

        /**
         * 初始化MMKV,只需要初始化一次，建议在Application中初始化
         *
         */
        val instance: MMkvHelper?
            get() {
                if (mInstance == null) {
                    synchronized(MMkvHelper::class.java) {
                        if (mInstance == null) {
                            mInstance = MMkvHelper()
                        }
                    }
                }
                return mInstance
            }
    }
}