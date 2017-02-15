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

import java.util.ArrayList;
import java.util.List;

import cn.com.sdq.smilefriends.R;
import cn.com.sdq.smilefriends.base.BaseFragment;
import cn.com.sdq.smilefriends.bean.JakeBean;
import cn.com.sdq.smilefriends.contact.Jake;
import cn.com.sdq.smilefriends.presenter.JakePresenter;
import cn.com.sdq.smilefriends.ui.adapter.MyRecyclerAdapter;

/**
 * Created by Jack on 16/10/27.
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
//        mJakePresenter.getJakeList();
    }

    public void setDatas(List<JakeBean> datas) {
        if (mDatas==null||mAdapter==null){
            initView();
            initData();
        }
        if (datas.size()>mDatas.size()){
            mDatas.addAll(datas);
            mAdapter.notifyDataSetChanged();
        }else {
            mDatas.clear();
            mDatas.addAll(datas);
            mAdapter.notifyDataSetChanged();

        }

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
        super.onResume();
        if (mDatas==null||mAdapter==null){
            initData();
            initView();

        }
    }

    @Override
    public void showContent(List<JakeBean> data) {
        Log.i(TAG,"显示数据");
        mDatas.addAll(data);
       mHandler.sendEmptyMessage(ADD_DATA);
    }

    @Override
    public void showMorejake(List<JakeBean> data) {

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
