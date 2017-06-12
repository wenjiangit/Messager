package com.example.factory;


import com.example.commom.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by wenjian on 2017/6/9.

 */

public class Factory {


    private final Executor executor;

    private static final Factory instance;

    static {
        instance = new Factory();
    }

    private Factory() {
        executor = Executors.newFixedThreadPool(4);
    }

    public static Application app() {
        return Application.getInstance();
    }

    public static void runOnUiAsync(Runnable runnable) {
        instance.executor.execute(runnable);
    }

}
