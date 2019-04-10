package zixun.xcb.example.zixun.ui.widget;

import com.chad.library.adapter.base.loadmore.LoadMoreView;

import zixun.xcb.example.zixun.R;

public class RvLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId(){
        return R.layout.item_load_more;
    }

    @Override
    public int getLoadingViewId(){
        return R.id.load_more_loading_view;
    }

    @Override
    public int getLoadFailViewId(){
        return R.id.load_more_load_fail_view;
    }

    @Override
    public int getLoadEndViewId(){
        return R.id.load_more_load_end_view;
    }
}
