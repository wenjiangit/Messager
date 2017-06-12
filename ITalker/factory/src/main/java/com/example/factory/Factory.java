package com.example.factory;

<<<<<<< HEAD
import com.example.commom.app.Application;

/**
 * Created by douliu on 2017/6/8.
=======

import com.example.commom.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by wenjian on 2017/6/9.
>>>>>>> a675bb9b7807148cb4f3deaf75c9a3387513ba24
 */

public class Factory {

<<<<<<< HEAD
    public static Application app() {
        return null;
    }
=======
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

>>>>>>> a675bb9b7807148cb4f3deaf75c9a3387513ba24
}
