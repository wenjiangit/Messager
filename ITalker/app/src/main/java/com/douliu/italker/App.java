package com.douliu.italker;

import com.example.commom.app.Application;
import com.example.factory.Factory;

/**
 * Created by wenjian on 2017/6/7.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Factory.setup();

//        PushManager.getInstance().initialize(this);

    }
}
