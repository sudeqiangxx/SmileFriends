package cn.com.sdq.smilefriends.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.sdq.smilefriends.R;
import cn.com.sdq.smilefriends.base.BaseFragment;
import cn.com.sdq.smilefriends.bean.JakeBean;
import cn.com.sdq.smilefriends.contact.Jake;
import cn.com.sdq.smilefriends.presenter.JakeGifPresenter;
import cn.com.sdq.smilefriends.ui.adapter.JakeGifAdapter;

/**
 * Created by Jack on 16/10/27.
 */
public class FragmentTwo extends BaseFragment implements Jake.View {
    @Bind(R.id.gif_fragment_recycler_view)
    RecyclerView mRecyclerView;
    //view和model的桥梁
    private Jake.Prestener prestener;
    //请求标记[0表示请求第一页，1表示请求更多]
    private int requestCode = 0;
    private View view;
    private JakeGifAdapter mAdapter;
    private List<JakeBean> mDatas = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;
    private static final int ADD_DATA = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ADD_DATA:
                    mAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_two, container, false);
        initData();
        initView();
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        prestener = new JakeGifPresenter(this);
        setPresenter(prestener);
        mDatas = new ArrayList<>();
        prestener.getJakeList();

    }

    public void setDatas(List<JakeBean> datas) {
        if (mDatas == null || mAdapter == null) {
            initView();
            initData();
        }
        if (datas.size() > mDatas.size()) {
            mDatas.addAll(datas);
            mAdapter.notifyDataSetChanged();
        } else {
            mDatas.clear();
            mDatas.addAll(datas);
            mAdapter.notifyDataSetChanged();

        }
    }

    private void initView() {
        mRecyclerView= (RecyclerView) view.findViewById(R.id.gif_fragment_recycler_view);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new JakeGifAdapter(getActivity(), mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visible = gridLayoutManager.getChildCount();
                int total = gridLayoutManager.getItemCount();
                int past = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
                if ((visible + past) >= total) {
                    requestCode = 1;
                    prestener.getMoreJake();
                }
            }
        });
    }

    @Override
    public void showContent(List<JakeBean> data) {
        if (mDatas != null) {
            mDatas.clear();
            mDatas.addAll(data);
        } else {
            mDatas = new ArrayList<>();
            mDatas.addAll(data);
        }
        //通知刷新数据
        mHandler.sendEmptyMessage(ADD_DATA);
    }

    @Override
    public void showMorejake(List<JakeBean> data) {
        if (mDatas != null) {
            mDatas.addAll(data);
        } else {
            mDatas = new ArrayList<>();
            mDatas.addAll(data);
        }
        mHandler.sendEmptyMessage(ADD_DATA);
        //通知刷新数据

    }

    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public void showFail(String msg) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
