package cn.com.sdq.smilefriends.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.com.sdq.smilefriends.R;
import cn.com.sdq.smilefriends.bean.JakeBean;
import cn.com.sdq.smilefriends.contact.Jake;
import cn.com.sdq.smilefriends.presenter.JakePresenter;
import cn.com.sdq.smilefriends.ui.adapter.JakeAdapter;

/**
 * Created by sdq on 22.01.2017.
 */
public class ContentFragment extends Fragment implements Jake.View {
    public static final String CLOSE = "Close";
    public static final String BUILDING = "Building";
    public static final String BOOK = "Book";
    public static final String PAINT = "Paint";
    public static final String CASE = "Case";
    public static final String SHOP = "Shop";
    public static final String PARTY = "Party";
    public static final String MOVIE = "Movie";

    private View containerView;
    protected int res;
    private Bitmap bitmap;
    private LinearLayoutManager mLayoutManager;
    private JakePresenter mPresent;
    private ArrayList<JakeBean> mDatas = new ArrayList<>();
    private JakeAdapter mAdapter;

    private RecyclerView recyclerView;

    private void initEvents() {
//        initRfL();
        initRLV();
        addListener();
    }

    private void addListener() {

    }

    private void initRLV() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new JakeAdapter(getActivity(), mDatas, recyclerView);
        recyclerView.setAdapter(mAdapter);
    }


    public static ContentFragment newInstance(int resId) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
//        initEvents();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getInt(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
//        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
//        sRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
    }

//    @Override
//    public void takeScreenShot() {
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
//                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
//                Canvas canvas = new Canvas(bitmap);
//                containerView.draw(canvas);
//                ContentFragment.this.bitmap = bitmap;
//            }
//        };
//
//        thread.start();
//
//    }

//    @Override
//    public Bitmap getBitmap() {
//        return bitmap;
//    }

    @Override
    public void showContent(List<JakeBean> data) {
//        if (sRefresh.isRefreshing()) {
//            sRefresh.setRefreshing(false);
//        }
//        mDatas.clear();
//        mDatas.addAll(data);
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMorejake(List<JakeBean> data) {
        mAdapter.loadCompleted();
        if (data != null && data.size() > 0) {

            mDatas.addAll(data);
            mAdapter.notifyItemRangeInserted(mDatas.size() - data.size(), data.size());
        } else {
            Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_LONG);
        }
    }

    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public void showFail(String msg) {
        mAdapter.loadCompleted();
//        if (sRefresh.isRefreshing()) {
//            sRefresh.setRefreshing(false);
//        }
//        Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_LONG);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

