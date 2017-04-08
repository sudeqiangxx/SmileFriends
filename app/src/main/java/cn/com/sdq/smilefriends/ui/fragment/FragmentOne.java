package cn.com.sdq.smilefriends.ui.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.com.sdq.smilefriends.R;
import cn.com.sdq.smilefriends.base.BaseFragment;
import cn.com.sdq.smilefriends.bean.JakeBean;
import cn.com.sdq.smilefriends.contact.Jake;
import cn.com.sdq.smilefriends.presenter.JakePresenter;
import cn.com.sdq.smilefriends.ui.adapter.MyRecyclerAdapter;
import cn.com.sdq.smilefriends.util.utils.Constants;
import cn.com.sdq.smilefriends.util.utils.L;

/**
 * Created by sudeqiang on 16/10/27.
 */
public class FragmentOne extends BaseFragment implements Jake.View{
    //view和数据交互枢纽
    private JakePresenter mJakePresenter;
    private LinearLayoutManager layoutManager;

    private static final String TAG=FragmentOne.class.getSimpleName();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private View view;
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mAdapter;
    private List<JakeBean> mDatas = new ArrayList<>();
    private static final int ADD_DATA=0;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case ADD_DATA:
                    mAdapter.notifyDataSetChanged();
                    hideWaitDialog();
                    break;
                default:break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one, container, false);
        initData();
        initView();
        L.i(TAG,"------------onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        L.i(TAG,"------------onViewCreated  view :"+view);

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.i(TAG,"------------onDestroy");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        L.i(TAG,"------------onDestroyView");


    }

    @Override
    public void initData() {
        super.initData();
        mJakePresenter=new JakePresenter(this);
        setPresenter(mJakePresenter);
        mJakePresenter.getJakeList();
        showWaitDialog();

    }

    public void setDatas(List<JakeBean> datas) {
//        if (mDatas==null||mAdapter==null){
//            initView();
//            initData();
//        }
        if (datas.size()>mDatas.size()){
            mDatas.addAll(datas);
            mAdapter.notifyDataSetChanged();
        }else {
            mDatas.clear();
            mDatas.addAll(datas);

            mAdapter.notifyDataSetChanged();

        }

    }
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(Map<Integer,List<JakeBean>> datas) {
        if (datas!=null&&datas.containsKey(Constants.PAGE_ONE)){
            List<JakeBean> data=datas.get(Constants.PAGE_ONE);
            L.i(TAG,"刷新数据");
            if (data.size()>mDatas.size()){
                mDatas.addAll(data);
                mAdapter.notifyDataSetChanged();
            }else {
                mDatas.clear();
                mDatas.addAll(data);
                mAdapter.notifyDataSetChanged();

            }
        }
//        EventBus.getDefault().cancelEventDelivery(datas) ;//优先级高的订阅者可以终止事件往下传递
    }

    private void initView() {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler_view);
        layoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyRecyclerAdapter(getActivity(), mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(40));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visible  = layoutManager.getChildCount();
                int total = layoutManager.getItemCount();
                int past=layoutManager.findFirstCompletelyVisibleItemPosition();
                if ((visible + past) >= total){
                    mJakePresenter.getMoreJake();
                }
            }
        });
    }

    @Override
    public void onResume() {
        L.i(TAG,"------------onResume");

        super.onResume();
        if (mDatas==null||mAdapter==null){
            initData();
            initView();

        }
    }

    @Override
    public void showContent(List<JakeBean> data) {
        Log.i(TAG,"显示数据");
        if (mDatas!=null&&mDatas.size()>0){
            mDatas.clear();
            mDatas.addAll(data);
        }else {
            mDatas.addAll(data);
        }

       mHandler.sendEmptyMessage(ADD_DATA);
    }

    @Override
    public void showMorejake(List<JakeBean> data) {
        mDatas.addAll(data);
        mHandler.sendEmptyMessage(ADD_DATA);

    }

    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public void showFail(String msg) {

    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            if(parent.getChildPosition(view) != 0)
                outRect.top = space;
        }
    }

}
