package com.example.blog.models.response;

import java.util.List;

public class ResPost  {

    public int code;
    public String msg;
    public List<Data> data;

    @Override
    public String toString() {
        return "ResPost{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
