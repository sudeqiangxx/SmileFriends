package cn.com.sdq.smilefriends.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.com.sdq.smilefriends.R;


/**
 * Created by Jack on 16/10/27.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView tvContent;
    public TextView tvTime;

    public MyViewHolder(View itemView) {
        super(itemView);
        tvContent = (TextView) itemView.findViewById(R.id.ir_tv_content);
        tvTime= (TextView) itemView.findViewById(R.id.tv_time);
    }
}
