package zixun.xcb.example.zixun.ui.activity;


import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import zixun.xcb.example.zixun.R;

import zixun.xcb.example.zixun.adapter.MainFragmentAdapter;

public class MainActivity extends FragmentActivity {

    /*
       是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，
       如果不使用则使用透明色值
     */
    protected boolean useThemestatusBarColor = false;
    /*
       是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色
     */
    protected boolean useStatusBarColor = true;

    /*
    菜单标题
     */

    private final int[] TAB_TITLES = new int[]{
            R.string.menu_main, R.string.menu_cargo,
            R.string.menu_find, R.string.menu_me};
    /*
    菜单图标
     */
    private final int[] TAB_IMGS = new int[]{
            R.drawable.img_menu_zhihu_selector, R.drawable.img_menu_wechat_selector,
            R.drawable.img_menu_find_selector, R.drawable.img_menu_me_selector};

    @BindView(R.id.view_page)
    ViewPager viewPage;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    /*
    页卡适配器
     */
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化页卡
        initPager();

        setTabs(tabLayout,getLayoutInflater(),TAB_TITLES,TAB_IMGS);
      //  setStatusBar();
    }

    /*
      * 设置页卡显示效果
      * @param tablayout
      * @param inflater
      * @param tabTitles
      * @param tabImgs
     */
    private void setTabs(TabLayout tabLayout, LayoutInflater inflater,
                         int[] tabTitles,int[] tabImgs){
        for(int i=0;i<tabImgs.length;i++){
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.item_main_menu,null);
            //使用自定义视图，目的是为了便于修改，也可使用自带的视图
            tab.setCustomView(view);

            TextView tvTitle = (TextView) view.findViewById(R.id.txt_tab);
            tvTitle.setText(tabTitles[i]);
            ImageView imgTab = (ImageView) view.findViewById(R.id.img_tab);
            imgTab.setImageResource(tabImgs[i]);
            tabLayout.addTab(tab);
        }
    }

    private void initPager(){
        adapter = new MainFragmentAdapter(getSupportFragmentManager());
        viewPage.setAdapter(adapter);

        //关联切换
        viewPage.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //取消平滑切换
                viewPage.setCurrentItem(tab.getPosition(),false);
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    /*
       设置沉浸式状态栏
     */
    protected void setStatusBar(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面的设置是否对状态栏单独设置颜色
            if(useThemestatusBarColor){
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorTheme));
                //设置状态栏背景色
            }
            else{
                getWindow().setStatusBarColor(Color.TRANSPARENT);
                //透明
            }
        }
        else if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }else{
            Toast.makeText(this,"低于4.4的Android版本不存在沉浸式状态栏"
            ,Toast.LENGTH_SHORT).show();
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && useStatusBarColor){//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
