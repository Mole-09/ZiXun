package zixun.xcb.example.zixun.ui.Fragment.find;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zixun.xcb.example.zixun.R;

public class FindFragment extends Fragment {
    public FindFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_find,container,false);
    }
}
