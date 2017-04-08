package cn.com.sdq.smilefriends.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.sdq.smilefriends.R;
import cn.com.sdq.smilefriends.base.BaseFragment;

/**
 * Created by Administrator on 2017/2/11.
 */

public class MineFragment extends BaseFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mine_fragmentlayout,container,false);
        return view;
    }
}
