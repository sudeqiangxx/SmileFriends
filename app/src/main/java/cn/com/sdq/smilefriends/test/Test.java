package cn.com.sdq.smilefriends.test;

import android.util.Log;

import java.io.IOException;

import cn.com.sdq.smilefriends.commn.RetrofitHelper;
import cn.com.sdq.smilefriends.logger.LoggerFactory;
import retrofit2.Call;

/**
 * Created by Administrator on 2016/12/25.
 */

public class Test {
    public static void main(String[] args) {
        RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
        Call<String> call=retrofitHelper.getJakeContent(1, System.currentTimeMillis());
        try {
            String content=call.execute().body();
            LoggerFactory.getLogger("").info(content);
            Log.i("请求后的内容：","------------"+content);
            System.out.print("请求后的内容："+content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}