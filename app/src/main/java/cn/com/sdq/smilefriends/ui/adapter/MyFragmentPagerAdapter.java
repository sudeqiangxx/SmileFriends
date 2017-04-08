package cn.com.sdq.smilefriends.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import cn.com.sdq.smilefriends.ui.fragment.FragmentOne;
import cn.com.sdq.smilefriends.ui.fragment.FragmentThree;
import cn.com.sdq.smilefriends.ui.fragment.FragmentTwo;


/**
 *
 */
public class MyFragmentPagerAdapter extends ViewPageFragmentAdapter {
    private String[] mTitles = new String[]{"最新笑话", "gif动图", "开心一笑"};

    public MyFragmentPagerAdapter(FragmentManager fm, ViewPager viewPager) {
        super(fm,viewPager,null,null);
    }

    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            if (fragmentTwo == null) {
                fragmentTwo = new FragmentTwo();
            }
            return fragmentTwo;
        } else if (position == 2) {
            if (fragmentThree == null) {
                fragmentThree = new FragmentThree();
            }
            return fragmentThree;
        } else {
            if (fragmentOne == null) {
                fragmentOne = new FragmentOne();
            }
            return fragmentOne;
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //用来设置tab的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
