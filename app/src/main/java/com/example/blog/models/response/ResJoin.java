package com.example.blog.models.response;

public class ResJoin {

    public int code;
    public String msg;
    public Data data;

    @Override
    public String toString() {
        return "ResJoin{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
