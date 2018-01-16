package com.jafir.jnitest;

import android.content.Context;

/**
 * Created by jafir on 2018/1/15.
 */


public class SIngle {
    private Context context_;
    private static SIngle instance_;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private SIngle(Context context) {
        context_ = context;
    }

    public static SIngle getInstance_(Context context) {
        if (instance_ == null) {
            instance_ = new SIngle(context.getApplicationContext());
        }
        return instance_;
    }

    @Override
    public String toString() {
        return "SIngle{" +
                "context_=" + context_ +
                ", name='" + name + '\'' +
                '}';
    }
}
