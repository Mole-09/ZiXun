package zixun.xcb.example.zixun.ui.Fragment.cargo.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zixun.xcb.example.zixun.R;

public class CargoContentFragment extends Fragment {

    @BindView(R.id.txt_content)
    TextView txtContent;
    private String name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        name = bundle.getString("name");
        if (name == null) {
            name = "参数非法";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cargo_content, container, false);
        ButterKnife.bind(this, view);
        txtContent.setText(name);
        return  view;

    }


}
