package cn.com.sdq.smilefriends.net;

import org.junit.Test;

/**
 * Created by Administrator on 2016/10/30.
 */
public class NetDataTest {
    @Test
    public void getRequest1() throws Exception {
//        NetData.getRequest1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetData.getRequest1();
            }
        }).start();

    }

    @Test
    public void getRequest2() throws Exception {

    }

    @Test
    public void getRequest3() throws Exception {

    }

    @Test
    public void getRequest4() throws Exception {

    }

    @Test
    public void net() throws Exception {

    }

    @Test
    public void urlencode() throws Exception {

    }

}