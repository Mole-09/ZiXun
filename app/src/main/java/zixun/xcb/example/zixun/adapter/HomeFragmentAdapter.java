package zixun.xcb.example.zixun.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import zixun.xcb.example.zixun.ui.Fragment.main.tab.BiZhiFragment;
import zixun.xcb.example.zixun.ui.Fragment.main.tab.WangYiFragment;
import zixun.xcb.example.zixun.ui.Fragment.main.tab.ZhiHuFragment;

public class HomeFragmentAdapter extends FragmentPagerAdapter {

    public HomeFragmentAdapter(FragmentManager fm){
        super(fm);
    }

    /*
      返回具体位置的ViewPager切换到具体位置时对应的fragment
     */
    @Override
    public Fragment getItem(int i){
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        switch (i){
            case 0:fragment = new ZhiHuFragment();break;
            case 1:fragment = new WangYiFragment();break;
            case 2:fragment = new BiZhiFragment();break;
            default:break;
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    /*
       返回视图的总数量
     */
    @Override
    public int getCount(){
        return 3;
    }

    String tabs[]={"知乎日报","网易新闻","壁纸"};
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }


}
