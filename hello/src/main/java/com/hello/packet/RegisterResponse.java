package com.hello.packet;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;

/**
 * @Author：lqh
 * @Date：2024/4/2 9:58
 */
@ProtobufClass
public class RegisterResponse {
    private long rid;
    private String userName;
    private String password;

    public static RegisterResponse valueOf(long rid, String userName, String password) {
        RegisterResponse response=new RegisterResponse();
        response.rid=rid;
        response.userName=userName;
        response.password=password;
        return response;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
