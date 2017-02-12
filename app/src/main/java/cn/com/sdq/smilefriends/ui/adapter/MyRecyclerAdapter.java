package cn.com.sdq.smilefriends.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.com.sdq.smilefriends.R;
import cn.com.sdq.smilefriends.bean.JakeBean;

/**
 * Created by sdq on 16/10/27.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context mContext;
    private List<JakeBean> mList;

    public MyRecyclerAdapter(Context mContext, List<JakeBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvContent.setText(mList.get(position).getContent());
        holder.tvTime.setText(mList.get(position).getUpdatetime());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
