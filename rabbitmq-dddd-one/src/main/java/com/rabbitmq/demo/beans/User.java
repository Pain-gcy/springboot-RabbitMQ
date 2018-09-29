package com.rabbitmq.demo.beans;

import java.io.Serializable;

/**
 * @author guochunyuan
 * @create on  2018-09-29 9:05
 */
public class User implements Serializable {
    private String userName;

    private String pwd;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
