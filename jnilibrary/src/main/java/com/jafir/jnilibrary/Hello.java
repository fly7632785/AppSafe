package com.jafir.jnilibrary;

/**
 * Created by jafir on 2018/1/8.
 */

public class Hello {
    static {
        System.loadLibrary("encrypt");
    }

    public static native String sayHello();
}
