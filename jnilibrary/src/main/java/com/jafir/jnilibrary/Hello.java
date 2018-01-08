package com.jafir.jnilibrary;

import java.util.Date;

/**
 * Created by jafir on 2018/1/8.
 */

public class Hello {
    static {
        System.loadLibrary("encrypt");
    }

    //定义数组
    public int[] arrays = new int[]{1,2,3,4,5,6};
    public Person[] persons = new Person[]{
            new Person("jafir1",1),
            new Person("jafir2",2),
            new Person("jafir3",3),
            new Person("jafir4",4)
    };



    public native void  callArray();

    public native String sayHello();

    public static native String staticSayHello();

    public native Person getPerson();

    public int property;

    public int function(int foo, Date date, int[] aar) {
        System.out.println("Function");
        return 0;
    }

    public static int staticFunction(int foo, Date date, int[] aar) {
        System.out.println("Static Function");
        return 0;
    }
}
