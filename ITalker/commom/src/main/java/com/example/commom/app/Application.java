package com.example.commom.app;

import android.os.SystemClock;

import java.io.File;

/**
 * Created by wenjian on 2017/6/7.
 */

public class Application extends android.app.Application {


    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Application getInstance() {
        return instance;
    }

    /**
     * 获取头像路径
     * @return
     */
    public static String getPortraitPath() {
        File cacheDir = instance.getCacheDir();
        File dir = new File(cacheDir.getAbsolutePath() + File.separator + "Portrait");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                file.delete();
            }
        }

        File portraitFile = new File(dir, SystemClock.uptimeMillis() + ".jpg");
        return portraitFile.getAbsolutePath();
    }
}
