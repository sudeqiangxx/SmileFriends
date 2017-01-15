package cn.com.sdq.smilefriends.commn;


import java.util.List;

import cn.com.sdq.smilefriends.bean.JakeBean;
import cn.com.sdq.smilefriends.util.utils.Constants;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by sdq on 2016/10/15.
 */

public class RetrofitHelper {

    private static RetrofitHelper mRetrofitHelper;
    private  JakeService mJakeListService;
    public RetrofitHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.NEW_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mJakeListService = retrofit.create(JakeService.class);

    }


    public static RetrofitHelper getInstance() {
        if (mRetrofitHelper == null) {
            synchronized (RetrofitHelper.class) {
                if (mRetrofitHelper == null) {
                    mRetrofitHelper = new RetrofitHelper();
                }
            }
        }
        return mRetrofitHelper;
    }






    /**
     * 获取笑话列表
     */
    public Observable<JakeHttppResponse<List<JakeBean>>> getJakeList(int page, long currentTime){
        if (mJakeListService==null){
            new RetrofitHelper();
        }
        //return mJakeListService.getJakelistResult(Constants.APP_KEY,page,Constants.SORT,currentTime);
        return null;
    }


    public Call<String> getJakeContent(int page, long currentTime){
        if (mJakeListService==null){
            new RetrofitHelper();
        }
        return mJakeListService.getJakelistResult(Constants.APP_KEY,page,Constants.SORT,currentTime);
    }

}
