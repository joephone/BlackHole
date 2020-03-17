package com.transcendence.blackhole.demo.LoginInterceptor.act;

/**
 * @Author Joephone on 2020/3/16 1:28
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 2.拦截器调度Invocation
 * @Edition 1.0
 * @EditionHistory
 */

public class Invocation {
    private IVew view;
    private Interceptor[] inters;
    private int index = 0;

    public Invocation(IVew view, Interceptor[] interceptors) {
        this.view = view;
        this.inters = interceptors;
    }

    public void invoke(){
        //如果拦截器数组有值，则回调当前拦截器的操作
        if(index<inters.length){
            inters[index++].intercept(this);
        //当当前拦截器执行完之后，又调用invoke方法，执行下一个拦截器，如果还有下一个拦截器，
        //继续执行intercept，否则才去回调当前activity的resume方法
        }else if(index++==inters.length){
            view.resume();
        }
    }
    public IVew getView() {
        return view;
    }

}
