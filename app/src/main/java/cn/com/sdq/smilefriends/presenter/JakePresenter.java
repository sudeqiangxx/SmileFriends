package cn.com.sdq.smilefriends.presenter;

import java.util.List;

import cn.com.sdq.smilefriends.bean.JakeBean;
import cn.com.sdq.smilefriends.contact.Jake;
import cn.com.sdq.smilefriends.net.JakeQuestData;
import cn.com.sdq.smilefriends.net.RequestListener;
import cn.com.sdq.smilefriends.util.utils.L;

/**
 * Created by sdq on 2016/12/6.
 */

public class JakePresenter implements Jake.Prestener {
    private Jake.View mView;
    private int currentPage=1;
    private static final String TAG=JakePresenter.class.getSimpleName();
    public JakePresenter(Jake.View mView) {
        this.mView = mView;
    }
    @Override
    public void getJakeList() {
        currentPage=1;
        final JakeQuestData jakeQuestData=new JakeQuestData(new RequestListener() {
            @Override
            public void requestSuccess(List<JakeBean> jakeBeanList) {
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
                if (mView!=null) {
                    L.i(TAG,"获取数据失败");
                    mView.showFail("获取数据失败");
                }
            }
        });
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        jakeQuestData.getNewJake(currentPage);
                    } catch (Exception e) {
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
            if (mView!=null) {
                mView.showFail("获取数据失败");
            }
        }

    }

    @Override
    public void getMoreJake() {
        currentPage++;
        final JakeQuestData jakeQuestData=new JakeQuestData(new RequestListener() {
            @Override
            public void requestSuccess(List<JakeBean> jakeBeanList) {
                if (mView!=null) {
                    if (jakeBeanList!=null) {

                        if (jakeBeanList.size()>0) {
                            //
                            mView.showMorejake(jakeBeanList);
                        }else {
                            mView.showFail("暂无数据");
                        }
                    }else {
                        mView.showFail("获取数据失败");
                    }
                }
            }

            @Override
            public void requestFailed(String requestContent) {
                if (mView!=null) {
                    mView.showFail("获取数据失败");
                }
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    jakeQuestData.getNewJake(currentPage);
                } catch (Exception e) {
                }
            }
        }).start();
//        try {
//            jakeQuestData.getJake(currentPage);
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (mView!=null) {
//                mView.showFail("获取数据失败");
//            }
//        }

    }

    @Override
    public void start() {

    }
}
