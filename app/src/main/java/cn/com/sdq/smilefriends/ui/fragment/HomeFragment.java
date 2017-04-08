package cn.com.sdq.smilefriends.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.sdq.smilefriends.R;
import cn.com.sdq.smilefriends.base.BaseFragment;
import cn.com.sdq.smilefriends.bean.JakeBean;
import cn.com.sdq.smilefriends.contact.Jake;
import cn.com.sdq.smilefriends.presenter.JakeGifPresenter;
import cn.com.sdq.smilefriends.presenter.JakePresenter;
import cn.com.sdq.smilefriends.ui.adapter.ViewPageFragmentAdapter;
import cn.com.sdq.smilefriends.ui.adapter.ViewPageInfo;
import cn.com.sdq.smilefriends.util.utils.Constants;
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
    private ArrayList<ViewPageInfo> viewPageInfos;
    private ArrayMap<String,Fragment> mFragments;
    private ViewPageFragmentAdapter myFragmentPagerAdapter;
    private List<JakeBean> mData;
    private FragmentManager fragmentManager;
    //view和数据交互工具
    private JakePresenter mJakePresenter;
    private Jake.Prestener mJakeGifPresenter;
    private Map<Integer,List<JakeBean>> refreshData;
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
        L.i(TAG,"--------------onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        L.i(TAG,"--------------onViewCreated");
        L.i(TAG,"--------------数据是否还是存在"+myFragmentPagerAdapter);

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
        L.i(TAG,"--------------onDestroy");




    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        L.i(TAG,"--------------onDestroyView");

    }

    @Override
    public void onResume() {
        super.onResume();
        L.i(TAG,"--------------onResume");


    }

    private void initView() {
//        setSupportActionBar(toolbar);   //把toolbar作为导航栏
        //设置下拉样式
        viewPageInfos=new ArrayList<>();
        initTabs();
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light
                , android.R.color.holo_red_light, android.R.color.holo_orange_light
                , android.R.color.holo_green_light);
        fragmentManager=getChildFragmentManager();
        myFragmentPagerAdapter = new ViewPageFragmentAdapter(fragmentManager,noScrollViewPager,viewPageInfos,mFragments);
//        noScrollViewPager.setAdapter(myFragmentPagerAdapter);
        noScrollViewPager.setNoScroll(true);//控制ViewPager是否可以左右滑动(true表示不可以)
        noScrollViewPager.setOffscreenPageLimit(6);
        //将TabLayout和ViewPager绑定在一起，使双方各自的改变都能直接影响另一方，解放了开发人员对双方变动事件的监听
        tabLayout.setupWithViewPager(noScrollViewPager);
        //指定Tab的位置
        tabOne = tabLayout.getTabAt(TAB_ONE_REFRESH);
        tabTwo = tabLayout.getTabAt(TAB_TWO_REFRESH);
        tabThree = tabLayout.getTabAt(TAB_THREE_REFRESH);
        initListener();
    }

    private void initTabs() {
        ViewPageInfo viewPageInfoJakeText=new ViewPageInfo("最新笑话","textfragment",FragmentOne.class);
        ViewPageInfo viewPageInfoJakeGif=new ViewPageInfo("gif动画","giffragment",FragmentTwo.class);
        ViewPageInfo viewPageInfoJake=new ViewPageInfo("最新笑话","jakefragment",FragmentThree.class);
        viewPageInfos.add(viewPageInfoJakeText);
        viewPageInfos.add(viewPageInfoJakeGif);
        viewPageInfos.add(viewPageInfoJake);
        mFragments=new ArrayMap<>();
        FragmentOne jakeTextFragment=new FragmentOne();
        FragmentTwo jakeGifFragment=new FragmentTwo();
        FragmentThree jakeFragment=new FragmentThree();
        mFragments.put("textfragment",jakeTextFragment);
        mFragments.put("giffragment",jakeGifFragment);
        mFragments.put("jakefragment",jakeFragment);
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
//    private Bundle getBundle(String state) {
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("org", getActivity().getArguments().getSerializable("one"));
//        bundle.putString("state", state);
//        return bundle;
//    }

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
//        ((FragmentOne) myFragmentPagerAdapter.getItem(TAB_ONE_REFRESH)).setDatas(jakeBeanList);
//        ((FragmentOne)fragmentManager.findFragmentByTag(FragmentOne.class.getSimpleName())).setDatas(jakeBeanList);
        refreshData=new HashMap<>();
        refreshData.put(Constants.PAGE_ONE,jakeBeanList);
        EventBus.getDefault().post(refreshData);
    }

    private void refreshTabTwoData(List<JakeBean> jakeBeanList) {
//        ((FragmentTwo) myFragmentPagerAdapter.getItem(TAB_TWO_REFRESH)).setDatas(jakeBeanList);
        refreshData=new HashMap<>();
        refreshData.put(Constants.PAGE_TWO,jakeBeanList);
        EventBus.getDefault().post(refreshData);
    }

    private void refreshTabThreeData(List<JakeBean> jakeBeanList) {
//        ((FragmentThree) myFragmentPagerAdapter.getItem(TAB_THREE_REFRESH)).setDatas(jakeBeanList);
        refreshData=new HashMap<>();
        refreshData.put(Constants.PAGE_THR,jakeBeanList);
        EventBus.getDefault().post(refreshData);
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
