package com.transcendence.freeland.basefun.reflection;

import android.widget.TextView;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.freeland.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *  反射
 *  获取类的信息（构造函数、字段、方法）。
 *  创建对象（包括调用私有构造函数）。
 *  访问和修改字段（包括私有字段）。
 *  调用方法（包括私有方法）。
 */
public class ReflectionActivity extends AppAc {

    TextView mTvInfo;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_basefun_reflection;
    }

    @Override
    protected void initView() {
        setTitle("反射");
        mTvInfo = findViewById(R.id.tv_info);
        StringBuilder sb = new StringBuilder();
        // 1. 获取类的 Class 对象
        Class<?> personClass = null;
        try {
            personClass = Class.forName("com.transcendence.freeland.basefun.reflection.Person");
            // 2. 获取类的构造函数并创建对象
            // 2.1 获取无参构造函数并创建对象
            Constructor<?> noArgConstructor = personClass.getConstructor();
            Object person1 = noArgConstructor.newInstance();
            System.out.println("Created object using no-arg constructor: " + person1);
            sb.append("Created object using no-arg constructor: " + person1+"/n");
            // 2.2 获取私有构造函数并创建对象
            Constructor<?> privateConstructor = personClass.getDeclaredConstructor(String.class, int.class);
            privateConstructor.setAccessible(true); // 设置可访问私有构造函数
            Object person2 = privateConstructor.newInstance("Alice", 25);
            System.out.println("Created object using private constructor: " + person2);
            sb.append("Created object using private constructor: " + person2+"/n");
            // 3. 获取并修改字段
            Field nameField = personClass.getDeclaredField("name");
            nameField.setAccessible(true); // 设置可访问私有字段
            nameField.set(person1, "Bob"); // 修改 person1 的 name 字段
            System.out.println("Updated name field of person1: " + nameField.get(person1));
            sb.append("Updated name field of person1: " + nameField.get(person1)+"/n");
            // 4. 调用方法
            // 4.1 调用公有方法
            Method sayHelloMethod = personClass.getMethod("sayHello");
            sayHelloMethod.invoke(person1); // 调用 person1 的 sayHello 方法

            // 4.2 调用私有方法
            Method privateMethod = personClass.getDeclaredMethod("privateMethod");
            privateMethod.setAccessible(true); // 设置可访问私有方法
            privateMethod.invoke(person2); // 调用 person2 的 privateMethod 方法
            mTvInfo.setText(sb+"");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
