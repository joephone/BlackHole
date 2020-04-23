package com.transcendence.network.jett.callback;

/**
 * @author Joephone on 2019/12/3 11:36
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface IError {
    /**
     * 逻辑错误
     * @param code
     * @param msg
     */
    void onError(int code, String msg);

}
