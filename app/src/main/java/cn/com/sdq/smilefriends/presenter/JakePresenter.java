package cn.com.sdq.smilefriends.presenter;

import java.util.List;

import cn.com.sdq.smilefriends.bean.JakeBean;
import cn.com.sdq.smilefriends.commn.JakeHttppResponse;
import cn.com.sdq.smilefriends.commn.RetrofitHelper;
import cn.com.sdq.smilefriends.contact.Jake;
import cn.com.sdq.smilefriends.util.utils.Constants;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sdq on 2016/12/6.
 */

public class JakePresenter implements Jake.Prestener {
    private Jake.View mView;
    private int currentPage=1;
    public JakePresenter(Jake.View mView) {
        this.mView = mView;
    }
    @Override
    public void getJakeList() {
        currentPage=1;
        RetrofitHelper.getInstance()
        .getJakeList(currentPage, Constants.NUM_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JakeHttppResponse<List<JakeBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView!=null) {
                            mView.showFail("获取数据失败");
                        }
                    }

                    @Override
                    public void onNext(JakeHttppResponse<List<JakeBean>> httppResponse) {
                        if (mView!=null) {
                            if (!httppResponse.getError()) {

                                if (httppResponse.getResults()!=null&&httppResponse.getResults().size()>0) {
                                    mView.showContent(httppResponse.getResults());
                                }else {
                                    mView.showFail("暂无数据");
                                }
                            }else {
                                mView.showFail("获取数据失败");
                            }
                        }
                    }
                });

    }

    @Override
    public void getMoreJake() {
        currentPage++;
        RetrofitHelper.getInstance()
                .getJakeList(currentPage, Constants.NUM_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JakeHttppResponse<List<JakeBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showFail(e.toString());
                    }

                    @Override
                    public void onNext(JakeHttppResponse<List<JakeBean>> httppResponse) {
                        if (mView!=null) {
                            mView.showMorejake(httppResponse.getResults());
                        }
                    }
                });
    }

    @Override
    public void start() {

    }
}
