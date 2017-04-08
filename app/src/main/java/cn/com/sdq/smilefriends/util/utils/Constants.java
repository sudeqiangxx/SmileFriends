package cn.com.sdq.smilefriends.util.utils;

import java.io.File;

import cn.com.sdq.smilefriends.manager.AppApi;

/**
 * Created by Administrator on 2016/12/10.
 */

public class Constants {

    public static final String PATH_CACHE="http-cache";
    public static final String PATH_IMG= AppApi.getInstance().getCacheDir().getAbsolutePath() + File.separator + "image";
    public static final String APP_KEY="4038445a90fbbf1fd6e286307751d0b0";
    public static final int  CODE_CALENDAR=101;
    public static final String EID="eid";
    public static final String HISTORY_BEAN="history_bean";
    public static final String DATE="date";
    public static final int NUM_PAGE=16;
    public static final String URL_IMG="img_url";
    public static final String DATA="data";
    public static final String SORT="desc";

    public static final int PAGE_ONE=1;
    public static final int PAGE_TWO=2;
    public static final int PAGE_THR=3;

}
