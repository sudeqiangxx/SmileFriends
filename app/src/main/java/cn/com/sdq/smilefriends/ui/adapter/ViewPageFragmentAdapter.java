package cn.com.sdq.smilefriends.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Map;

@SuppressLint("Recycle")
public class ViewPageFragmentAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    private final ViewPager mViewPager;
    public ArrayList<ViewPageInfo> mTabs = new ArrayList<ViewPageInfo>();
    private Map<String, Fragment> mFragments = new ArrayMap<>();
    private FragmentManager fm;

    public ViewPageFragmentAdapter(FragmentManager fm,
                                   ViewPager pager, ArrayList<ViewPageInfo> tabs,ArrayMap<String,Fragment> mFragments) {
        super(fm);
        this.fm=fm;
        mContext = pager.getContext();
        mTabs=tabs;
        mViewPager = pager;
        mViewPager.setAdapter(this);
        this.mFragments=mFragments;
    }


    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        ViewPageInfo info = mTabs.get(position);

        Fragment fragment = mFragments.get(info.tag);
//        if (fragment == null) {
//         //   fragment = Fragment.instantiate(mContext, info.clss.getName(), info.args);
//            // 避免重复创建而进行缓存
//            mFragments.put(info.tag, fragment);
//        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position).title;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        Object obj = super.instantiateItem(container, position);
        return obj;
    }
}