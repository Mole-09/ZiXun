package zixun.xcb.example.zixun.ui.Fragment.cargo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zixun.xcb.example.zixun.R;
import zixun.xcb.example.zixun.adapter.CargoContentAdapter;

/*
  在这个界面中实现干货的顶端选择栏
 */
public class CargoFragment extends Fragment {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    public CargoFragment() {
    }

    private CargoContentAdapter adapter;
    private List<String> names;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cargo, container, false);
        ButterKnife.bind(this, view);
        adapter = new CargoContentAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //更新适配器的数据
        adapter.setList(names);
        return view;
    }

    private void initData(){
        names = new ArrayList<>();
        names.add("关注");
        names.add("推荐");
        names.add("热点");
        names.add("视频");
        names.add("小说");
        names.add("娱乐");
        names.add("问答");
        names.add("图片");
        names.add("科技");
        names.add("懂车帝");
        names.add("体育");
        names.add("财经");
        names.add("军事");
        names.add("国际");
        names.add("健康");

    }


}
