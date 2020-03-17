package com.transcendence.blackhole.demo.LoginInterceptor.act;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Joephone on 2020/3/16 1:45
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class InterceptorBuilder {
    public static final Interceptor[] NULL_INTERS = new Interceptor[0];

    private static Map<Class<? extends Interceptor>, Interceptor> intersMap = new HashMap<>();

    /**
     * 构建 Interceptors.
     * finalInters = globalInters + classInters
     */
    public static Interceptor[] build(Interceptor[] globalInters, Class<?> targetClass) {
        //1.存入全局的拦截器，添加到Map集合中
        for (Interceptor inter : globalInters)
            intersMap.put(inter.getClass(), inter);

        //2.获取Before注释的拦截器,如果有添加到Map集合中
        Interceptor[] classInters = createInterceptors(targetClass.getAnnotation(Before.class));

        // 3.判断是否有Clear注释
        Clear clear = targetClass.getAnnotation(Clear.class);

        //① 如果为null，就说明没有Clear ，直接将全局和Before构造成一个新的拦截器数组
        if (clear == null) {
            Interceptor[] result = new Interceptor[globalInters.length + classInters.length];
            int index = 0;
            for (Interceptor inter : globalInters)
                result[index++] = inter;
            for (Interceptor inter : classInters)
                result[index++] = inter;
            return result;
        }


        Class<? extends Interceptor>[] clearInters = clear.value();

        // ② 有Clear拦截器的标志，但是后面没有拦截器参数，意味着清楚所有其他的全局拦截器
        // （全局拦截器有很多，当不需要时，clear标志清除)，只保留当前类Before拦截器
        if (clearInters.length == 0)
            return classInters;

        // ③ 有Clear拦截器的标志，后面也有Clear拦截器 则删除Map中Clear指定的拦截器，返回
        Interceptor[] temp = new Interceptor[globalInters.length + classInters.length];
        int index = 0;
        for (Interceptor inter : globalInters)
            temp[index++] = inter;
        for (Interceptor inter : classInters)
            temp[index++] = inter;

        int removeCount = 0;
        for (int i=0; i<temp.length; i++) {
            for (Class<? extends Interceptor> ci : clearInters) {
                if (temp[i].getClass() == ci) {//如果全局和当前类中有Clear标志的拦截器，置为null
                    temp[i] = null;
                    removeCount++;
                    break;
                }
            }
        }

        Interceptor[] result = new Interceptor[temp.length  - removeCount];//创建一个新的所有的拦截器数组，进行返回
        index = 0;
        for (Interceptor inter : temp)
            if (inter != null)
                result[index++] = inter;
        return result;
    }

    private static Interceptor[] createInterceptors(Before beforeAnnotation) {
        //为before为null，返回长度为0的数组
        if (beforeAnnotation == null)
            return NULL_INTERS;

        Class<? extends Interceptor>[] interceptorClasses = beforeAnnotation.value();
        //before注释后面没有拦截器的时候,返回长度为0的数组
        if (interceptorClasses.length == 0)
            return NULL_INTERS;


        //如果Map集合，全局拦截器没有该类上面的拦截器，就加入Map集合
        Interceptor[] result = new Interceptor[interceptorClasses.length];
        try {
            for (int i=0; i<result.length; i++) {
                result[i] = intersMap.get(interceptorClasses[i]);
                if (result[i] == null) {
                    result[i] = (Interceptor)interceptorClasses[i].newInstance();
                    intersMap.put(interceptorClasses[i], result[i]);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
