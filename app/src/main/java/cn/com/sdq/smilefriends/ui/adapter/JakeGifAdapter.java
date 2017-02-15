package cn.com.sdq.smilefriends.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cunoraz.gifview.library.GifView;

import java.util.List;

import cn.com.sdq.smilefriends.R;
import cn.com.sdq.smilefriends.bean.JakeBean;
import cn.com.sdq.smilefriends.util.utils.StringUtil;

/**
 * Created by Administrator on 2017/2/16.
 */

public class JakeGifAdapter extends RecyclerView.Adapter<JakeGifAdapter.GifHolder> {
    private Context mContext;
    private List<JakeBean> mList;
    public JakeGifAdapter(Context context,List<JakeBean> list){
        this.mContext = context;
        this.mList = list;
    }
    @Override
    public GifHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_gif, null);
        return new GifHolder(view);
    }

    @Override
    public void onBindViewHolder(GifHolder holder, int position) {
            if (mList!=null&&mList.size()>0&& !StringUtil.isNullOrEmpty(mList.get(position).getContent()))
                holder.tvContent.setText(mList.get(position).getContent());
            //显示gif
    }



    @Override
    public int getItemCount() {
        return mList.size();
    }
    class GifHolder extends RecyclerView.ViewHolder{
        TextView tvContent;
        GifView showGifview;
        public GifHolder(View itemView) {
            super(itemView);
            tvContent= (TextView) itemView.findViewById(R.id.tv_show_gifcontent);
//            showGifview= (GifView) itemView.findViewById(R.id.gif1);
        }
    }
}
