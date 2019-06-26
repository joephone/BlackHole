package com.transcendence.blackhole.ui.mvp.bean;

/**
 * @author Joephone on 2019/6/25 15:31
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class User {

    private String name;
    private String pwd;

    public User(String name, String pwd){
        this.name = name;
        this.pwd = pwd;
    }

    public String getName(){return name;}

    public void setName(String name) {this.name = name;}

    public String getPwd() {return pwd;}

    public void setPwd(String pwd) {this.pwd = pwd;}
}
