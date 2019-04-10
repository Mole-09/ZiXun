package zixun.xcb.example.zixun.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import zixun.xcb.example.zixun.ui.Fragment.cargo.child.CargoContentFragment;

/*
  因为内容页大多类似，所以采用同一个Fragment布局即可，内容根据传参来修改
 */
public class CargoContentAdapter extends FragmentPagerAdapter{
    private List<String> names;

    public CargoContentAdapter(FragmentManager fm){
        super(fm);
        this.names = new ArrayList<>();
    }

    /*
       数据列表
       @param datas
     */

    public void setList(List<String> datas){
        this.names.clear();
        this.names.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position){
        CargoContentFragment fragment = new CargoContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",names.get(position));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount(){
        return names.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        String plateName = names.get(position);
        if(plateName == null){
            plateName ="";
        }
        else if(plateName.length()>15){
            plateName = plateName.substring(0,15)+"...";
        }
        return plateName;
    }
}
