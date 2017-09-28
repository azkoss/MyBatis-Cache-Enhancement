package com.github.wanggit.mybatis.cache.enhancement.utils;

public abstract class ThreadUtils {

    public static void pause(int timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentThreadInfo(){
        Thread current = Thread.currentThread();
        return current.getId() + "#" + current.toString();
    }

}
