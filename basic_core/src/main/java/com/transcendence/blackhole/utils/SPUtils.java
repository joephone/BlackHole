package com.transcendence.blackhole.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.transcendence.blackhole.base.app.LibApplication;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Joephone on 2019/8/26 16:48
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class SPUtils {
    private static Map<String, SPUtils> sSPMap = new HashMap<>();
    private SharedPreferences sp;


    //	private final static String name = "words_config";  为默认操作模式，代表该文件是私有数据，只能被应用本身访问，在该模式下，写入的内容会覆盖原文件的内容，如果想把新写入的内容追加到原文件中。
    private final static int mode = Context.MODE_PRIVATE;

    /**
     * 获取SP实例
     *
     * @return {@link SPUtils}
     */
    public static SPUtils getInstance() {
        return getInstance("");
    }

    /**
     * 获取SP实例
     *
     * @param spName sp名
     * @return {@link SPUtils}
     */
    public static SPUtils getInstance(String spName) {
        if (isSpace(spName)) {spName = "spUtils";}
        SPUtils sp = sSPMap.get(spName);
        if (sp == null) {
            sp = new SPUtils(spName);
            sSPMap.put(spName, sp);
        }
        return sp;
    }

    private SPUtils(String spName) {
        if (LibApplication.getAppContext() != null) {
            sp = LibApplication.getAppContext().getSharedPreferences(spName, mode);
        }else {
            L.d("LibApplication.getAppContext() == null");
        }
    }

    /**
     * SP中写入String
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull String key, @NonNull String value) {
        sp.edit().putString(key, value).apply();
    }

    /**
     * SP中读取String
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code null}
     */
    public String getString(@NonNull String key) {
        return getString(key, "");
    }

    /**
     * SP中读取String
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public String getString(@NonNull String key, @NonNull String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    /**
     * SP中写入int
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    /**
     * SP中读取int
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public int getInt(@NonNull String key) {
        return getInt(key, -1);
    }

    /**
     * SP中读取int
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public int getInt(@NonNull String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    /**
     * SP中写入long
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull String key, long value) {
        sp.edit().putLong(key, value).apply();
    }

    /**
     * SP中读取long
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public long getLong(@NonNull String key) {
        return getLong(key, -1L);
    }

    /**
     * SP中读取long
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public long getLong(@NonNull String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    /**
     * SP中写入float
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull String key, float value) {
        sp.edit().putFloat(key, value).apply();
    }

    /**
     * SP中读取float
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public float getFloat(@NonNull String key) {
        return getFloat(key, -1f);
    }

    /**
     * SP中读取float
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public float getFloat(@NonNull String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    /**
     * SP中写入boolean
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    /**
     * SP中读取boolean
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code false}
     */
    public boolean getBoolean(@NonNull String key) {
        return getBoolean(key, false);
    }

    /**
     * SP中读取boolean
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * SP中写入String集合
     *
     * @param key    键
     * @param values 值
     */
    public void put(@NonNull String key, @NonNull Set<String> values) {
        sp.edit().putStringSet(key, values).apply();
    }

    /**
     * SP中读取StringSet
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code null}
     */
    public Set<String> getStringSet(@NonNull String key) {
        return getStringSet(key, Collections.<String>emptySet());
    }

    /**
     * SP中读取StringSet
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public Set<String> getStringSet(@NonNull String key, @NonNull Set<String> defaultValue) {
        return sp.getStringSet(key, defaultValue);
    }

    /**
     * SP中获取所有键值对
     *
     * @return Map对象
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    /**
     * SP中移除该key
     *
     * @param key 键
     */
    public void remove(@NonNull String key) {
        sp.edit().remove(key).apply();
    }

    /**
     * SP中是否存在该key
     *
     * @param key 键
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public boolean contains(@NonNull String key) {
        return sp.contains(key);
    }

    /**
     * SP中清除所有数据
     */
    public void clear() {
        sp.edit().clear().apply();
    }

    private static boolean isSpace(String s) {
        if (s == null) {return true;}
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    public <T> SPUtils save(String keyword, T value) {
        SharedPreferences.Editor editor = sp.edit();
        if (value == null) {
            editor.remove(keyword).apply();
        } else if (value instanceof String) {
            editor.putString(keyword, (String) value).apply();
        } else if (value instanceof Integer) {
            editor.putInt(keyword, (Integer) value).apply();
        } else if (value instanceof Boolean) {
            editor.putBoolean(keyword, (Boolean) value).apply();
        } else if (value instanceof Long) {
            editor.putLong(keyword, (Long) value).apply();
        } else if (value instanceof Float) {
            editor.putFloat(keyword, (Float) value).apply();
        }
        return this;
    }

    public <T> T get(String keyword, T defValue) {
        T value;
        if (defValue instanceof String) {
            String s = sp.getString(keyword, (String) defValue);
            value = (T) s;
        } else if (defValue instanceof Integer) {
            Integer i = sp.getInt(keyword, (Integer) defValue);
            value = (T) i;
        } else if (defValue instanceof Long) {
            Long l = sp.getLong(keyword, (Long) defValue);
            value = (T) l;
        } else if (defValue instanceof Float) {
            Float f = sp.getFloat(keyword, (Float) defValue);
            value = (T) f;
        } else if (defValue instanceof Boolean) {
            Boolean b = sp.getBoolean(keyword, (Boolean) defValue);
            value = (T) b;
        } else {
            value = defValue;
        }
        return value;
    }
}
