package zixun.xcb.example.zixun.ui.Fragment.me;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zixun.xcb.example.zixun.R;

public class MeFragment extends Fragment {

    public MeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_me,container,false);
    }
}
