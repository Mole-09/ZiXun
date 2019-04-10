package zixun.xcb.example.zixun.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import zixun.xcb.example.zixun.ui.Fragment.cargo.CargoFragment;
import zixun.xcb.example.zixun.ui.Fragment.find.FindFragment;
import zixun.xcb.example.zixun.ui.Fragment.main.MainFragment;
import zixun.xcb.example.zixun.ui.Fragment.me.MeFragment;

public class MainFragmentAdapter extends FragmentPagerAdapter {

    public MainFragmentAdapter(FragmentManager fm){
        super(fm);
    }

    /*
    返回具体位置的ViewPager切换到i位置时对应的fragment
     */
    @Override
    public Fragment getItem(int i){
        Fragment fragment = null;
        switch(i){
            case 0:fragment = new MainFragment();break;
            case 1:fragment = new CargoFragment();break;
            case 2:fragment = new FindFragment();break;
            case 3:fragment = new MeFragment();break;
            default:
                break;
        }
        return fragment;
    }

    /*
    返回视图的总数量
     */
    @Override
    public int getCount(){
        return  4;
    }
}
