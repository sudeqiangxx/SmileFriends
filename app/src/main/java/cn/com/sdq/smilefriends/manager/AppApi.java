package cn.com.sdq.smilefriends.manager;

import android.app.Application;

/**
 * Created by Administrator on 2016/12/7.
 */

public class AppApi extends Application {
    private static AppApi instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;

    }


    public static AppApi getInstance(){
        return instance;
    }
}
