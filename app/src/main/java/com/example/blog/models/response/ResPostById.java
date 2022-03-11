package com.example.blog.models.response;

public class ResPostById {

    public int code;
    public String msg;
    public Data data;

    @Override
    public String toString() {
        return "ResPostById{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
