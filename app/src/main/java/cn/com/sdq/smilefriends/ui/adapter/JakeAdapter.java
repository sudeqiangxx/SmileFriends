package cn.com.sdq.smilefriends.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.com.sdq.smilefriends.R;
import cn.com.sdq.smilefriends.bean.JakeBean;

/**
 * Created by Administrator on 2016/12/10.
 */

public class JakeAdapter extends RecyclerView.Adapter {
    private List<JakeBean> jakeBeanList;
    private Context mContext;
    private LayoutInflater inflater;
    private RecyclerView recyclerView;


    private final int item_type = 100;
    private final int load_type = 101;
    private OnItemClickListener mItemClickListener;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading = false;
    private boolean isFirst=true;
    public JakeAdapter(Context context, List<JakeBean> jakeBeanList,RecyclerView recyclerView){
        if (jakeBeanList!=null){
            this.jakeBeanList=jakeBeanList;
        }else {
            this.jakeBeanList=new ArrayList<>();
        }
        mContext=context;
        inflater=LayoutInflater.from(mContext);
        this.recyclerView=recyclerView;
        initRecyclerView();

    }

    private void initRecyclerView() {
        LinearLayoutManager linearlayoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int itemCount = layoutManager.getItemCount();
                if (lastVisibleItemPosition > itemCount - 2 && !isLoading && dy > 0) {
                    isLoading = true;
                    if (mOnLoadMoreListener!=null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                }
            }
        });

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == load_type) {
            View loadView = LayoutInflater.from(mContext).inflate(R.layout.load_view, parent, false);
            LoadViewHolder holder = new LoadViewHolder(loadView);
            return holder;

        } else {
            View jakeView = LayoutInflater.from(mContext).inflate(R.layout.item_jake, parent, false);
            JakeViewHolder contentHolder=new JakeViewHolder(jakeView);
            return contentHolder;

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return jakeBeanList.size() + 1;
    }
    @Override
    public int getItemViewType(int position) {
        if (position == jakeBeanList.size()) {
            return load_type;
        } else {
            return item_type;
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mOnLoadMoreListener = listener;
    }

    public void loadCompleted() {
        isLoading = false;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    public interface OnLoadMoreListener {
        void onLoadMore();
    }
    class LoadViewHolder extends RecyclerView.ViewHolder {
        public LoadViewHolder(View itemView) {
            super(itemView);
        }
    }
    class JakeViewHolder extends RecyclerView.ViewHolder{
        public JakeViewHolder(View itemView) {
            super(itemView);
        }
    }

}
