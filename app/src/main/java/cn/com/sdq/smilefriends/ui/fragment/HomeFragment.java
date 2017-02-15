package cn.com.sdq.smilefriends.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.sdq.smilefriends.R;
import cn.com.sdq.smilefriends.base.BaseFragment;
import cn.com.sdq.smilefriends.bean.JakeBean;
import cn.com.sdq.smilefriends.contact.Jake;
import cn.com.sdq.smilefriends.presenter.JakeGifPresenter;
import cn.com.sdq.smilefriends.presenter.JakePresenter;
import cn.com.sdq.smilefriends.ui.adapter.MyFragmentPagerAdapter;
import cn.com.sdq.smilefriends.util.utils.L;
import cn.com.sdq.smilefriends.util.utils.T;
import cn.com.sdq.smilefriends.widgte.NoScrollViewPager;

/**
 * Created by 苏德强 on 2017/2/11.
 */

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, Jake.View{


    public static final int TAB_ONE_REFRESH = 0;
    public static final int TAB_TWO_REFRESH = 1;
    public static final int TAB_THREE_REFRESH = 2;
    private static final String TAG=HomeFragment.class.getSimpleName();

    @Bind(R.id.am_srl_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.lmd_nsp_viewpager)
    NoScrollViewPager noScrollViewPager;
    @Bind(R.id.lmd_app_bar_layout)
    AppBarLayout appBarLayout;
    @Bind(R.id.lmd_toolbar)
    Toolbar toolbar;
    @Bind(R.id.lmd_tl_tablayout)
    TabLayout tabLayout;
    TabLayout.Tab tabOne;
    TabLayout.Tab tabTwo;
    TabLayout.Tab tabThree;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private List<JakeBean> mData;
    //view和数据交互工具
    private JakePresenter mJakePresenter;
    private Jake.Prestener mJakeGifPresenter;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case TAB_ONE_REFRESH:
                    hideWaitDialog();
                    refreshTabOneData(mData);
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                case TAB_TWO_REFRESH:
                    hideWaitDialog();
                    refreshTabTwoData(mData);
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                case TAB_THREE_REFRESH:
                    break;
                default:break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,
                container, false);
        ButterKnife.bind(this, view);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
        mJakePresenter=new JakePresenter(this);
        setPresenter(mJakePresenter);
        mJakeGifPresenter=new JakeGifPresenter(this);
        setPresenter(mJakeGifPresenter);
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        initView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initView() {
//        setSupportActionBar(toolbar);   //把toolbar作为导航栏
        //设置下拉样式
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light
                , android.R.color.holo_red_light, android.R.color.holo_orange_light
                , android.R.color.holo_green_light);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        noScrollViewPager.setAdapter(myFragmentPagerAdapter);
        noScrollViewPager.setNoScroll(true);    //控制ViewPager是否可以左右滑动(true表示不可以)

        //将TabLayout和ViewPager绑定在一起，使双方各自的改变都能直接影响另一方，解放了开发人员对双方变动事件的监听
        tabLayout.setupWithViewPager(noScrollViewPager);
        //指定Tab的位置
        tabOne = tabLayout.getTabAt(TAB_ONE_REFRESH);
        tabTwo = tabLayout.getTabAt(TAB_TWO_REFRESH);
        tabThree = tabLayout.getTabAt(TAB_THREE_REFRESH);
        initListener();
    }

    private void initListener() {
        swipeRefreshLayout.setOnRefreshListener(this);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    swipeRefreshLayout.setEnabled(true);
                } else {
                    swipeRefreshLayout.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        switch (noScrollViewPager.getCurrentItem()) {
            case TAB_ONE_REFRESH:
                L.i(TAG,"开始刷新获取笑话数据");
                showWaitDialog();
               mJakePresenter.getJakeList();
                break;
            case TAB_TWO_REFRESH:
               mJakeGifPresenter.getJakeList();
                break;
            case TAB_THREE_REFRESH:
               mJakePresenter.getJakeList();
                break;
        }
    }

    private void refreshTabOneData(List<JakeBean> jakeBeanList) {
        ((FragmentOne) myFragmentPagerAdapter.getItem(TAB_ONE_REFRESH)).setDatas(jakeBeanList);
    }

    private void refreshTabTwoData(List<JakeBean> jakeBeanList) {
        ((FragmentTwo) myFragmentPagerAdapter.getItem(TAB_TWO_REFRESH)).setDatas(jakeBeanList);
    }

    private void refreshTabThreeData(List<JakeBean> jakeBeanList) {
        ((FragmentThree) myFragmentPagerAdapter.getItem(TAB_THREE_REFRESH)).setDatas(jakeBeanList);
    }

    @Override
    public void showContent( List<JakeBean> data) {
        switch (noScrollViewPager.getCurrentItem()) {
            case TAB_ONE_REFRESH:
                L.i(TAG,"反馈数据成功刷新数据");
                for (JakeBean j:data
                        ) {
                    L.i(TAG,"反馈数据成功刷新数据"+j.toString());
                }
                mData=data;
                mHandler.sendEmptyMessage(TAB_ONE_REFRESH);
                break;
            case TAB_TWO_REFRESH:
//                L.i(TAG,"反馈数据成功刷新数据");
                for (JakeBean j:data
                        ) {
                    L.i(TAG,"反馈数据成功刷新数据"+j.toString());
                }
                mData=data;
                mHandler.sendEmptyMessage(TAB_TWO_REFRESH);
                break;
            case TAB_THREE_REFRESH:

                break;
        }
    }

    @Override
    public void showMorejake(List<JakeBean> data) {

    }

    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public void showFail(String msg) {
        T.ShowToastForShort(getActivity(),msg.toString());
    }
}
