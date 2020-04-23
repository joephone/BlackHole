package com.transcendence.config;

import java.util.HashMap;

/**
 * @author Joephone on 2019/10/30 14:47
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 构造里没有准备好，当把各种配置信息配置之后,call configuratorReady()
 * @Edition 1.0
 * @EditionHistory
 */

public class Configurator {

    /**
     * 加final 内存空间不会变化  地址不会变化
     */
    private static final HashMap<Object,Object> CONFIGS = new HashMap<>();

    /**
     * 静态内部类 性能比较好
     */
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    /**
     * 构造方法
     */
    private Configurator() {
        CONFIGS.put(ConfigKeys.CONFIG_READY.name(),false);
        //回调相关信息填完之后改为true
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    /**
     *获取配置信处
     * default 修饰符 同类同包可以访问
     */
    final HashMap<Object,Object> getConfigs(){
        return CONFIGS;
    }

    /**
     * 配置API_HOST
     */
    public final Configurator withApiHost(String host) {
        CONFIGS.put(ConfigKeys.API_HOST,host);
        return this;
    }

    /**
     * 完成配置
     */
    public final void configurator(){
        CONFIGS.put(ConfigKeys.CONFIG_READY.name(),true);
    }

    /**
     * 检查配置是否完成
     */
    private final void checkConfiguration(){
        final boolean isReady = (boolean) CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        //如果没准备好，则抛出运行时异常调用configurator();
        if(!isReady){
            throw new RuntimeException("configurator is not Ready call configurator()");
        }
    }

    /**
     * 得到配置信息
     */
    final <T>T getConfiguratorByKey(Object key){
        checkConfiguration();
        final Object config = CONFIGS.get(key);
        if (config == null) {
            throw new NullPointerException(key.toString()+"IS NULL");
        }
        return (T) config;
    }


}
