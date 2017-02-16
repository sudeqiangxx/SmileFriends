package cn.com.sdq.smilefriends.presenter;

import java.util.List;

import cn.com.sdq.smilefriends.bean.JakeBean;
import cn.com.sdq.smilefriends.contact.Jake;
import cn.com.sdq.smilefriends.net.JakeQuestData;
import cn.com.sdq.smilefriends.net.RequestListener;
import cn.com.sdq.smilefriends.util.utils.L;

/**
 * Created by sudeqiang on 2017/2/15.
 *
 * 对笑话gif数据的请求，并对请求进行处理
 */

public class JakeGifPresenter implements Jake.Prestener{
    //持有view实例，对view进行数据反馈更新
    private Jake.View mView;
    private int currentPage=1;
    private String TAG=JakeGifPresenter.class.getSimpleName();
    public  JakeGifPresenter(Jake.View mView){
        this.mView=mView;
    }
    /**
     * 获取数据
     */
    @Override
    public void getJakeList() {
        currentPage=1;
        final JakeQuestData jakeQuestData=new JakeQuestData(new RequestListener() {
            @Override
            public void requestSuccess(List<JakeBean> jakeBeanList) {
                //请求数据成功
                if (mView!=null) {
                    if (jakeBeanList!=null) {

                        if (jakeBeanList.size()>0) {
                            //
                            L.i(TAG,"获取数据成功");
                            for (JakeBean j:jakeBeanList
                                    ) {
                                L.i(TAG,"获取数据成功"+ j.toString());
                            }
                            mView.showContent(jakeBeanList);



                        }else {
                            L.i(TAG,"没有数据");
                            mView.showFail("暂无数据");
                        }
                    }else {
                        L.i(TAG,"获取数据失败");
                        mView.showFail("获取数据失败");
                    }
                }
            }

            @Override
            public void requestFailed(String requestContent) {
                //请求数据失败
                if (mView!=null) {
                    L.i(TAG,"获取数据失败");
                    mView.showFail("获取数据失败");
                }
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    jakeQuestData.getNewJakePhoto(currentPage);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * 获取更多数据
     */
    @Override
    public void getMoreJake() {
        currentPage++;
        final JakeQuestData jakeQuestData=new JakeQuestData(new RequestListener() {
            @Override
            public void requestSuccess(List<JakeBean> jakeBeanList) {
                //请求数据成功
                if (mView!=null) {
                    if (jakeBeanList!=null) {

                        if (jakeBeanList.size()>0) {
                            //
                            L.i(TAG,"获取数据成功");
                            for (JakeBean j:jakeBeanList
                                    ) {
                                L.i(TAG,"获取数据成功"+ j.toString());
                            }
                            mView.showMorejake(jakeBeanList);



                        }else {
                            L.i(TAG,"没有数据");
                            mView.showFail("暂无数据");
                        }
                    }else {
                        L.i(TAG,"获取数据失败");
                        mView.showFail("获取数据失败");
                    }
                }
            }

            @Override
            public void requestFailed(String requestContent) {
                //请求数据失败
                if (mView!=null) {
                    L.i(TAG,"获取数据失败");
                    mView.showFail("获取数据失败");
                }
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    jakeQuestData.getNewJakePhoto(currentPage);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void start() {

    }
}
