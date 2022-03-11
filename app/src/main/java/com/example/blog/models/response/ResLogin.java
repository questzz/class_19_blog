package com.example.blog.models.response;

public class ResLogin {

    public int code;
    public String msg;
    public Data data;

    @Override
    public String toString() {
        return "ResLogin{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
