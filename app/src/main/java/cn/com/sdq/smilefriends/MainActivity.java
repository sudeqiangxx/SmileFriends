package cn.com.sdq.smilefriends;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.com.sdq.smilefriends.net.NetData;
import cn.com.sdq.smilefriends.util.okhttp;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetData.getRequest2();
          }
        }).start();
       OkHttpClient httpclent=okhttp.getmOkHttpClientInstance();



    }
}
