package zixun.xcb.example.zixun.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


import zixun.xcb.example.zixun.R;
import zixun.xcb.example.zixun.model.bean.zhihu.ZhihuItemBean;

public class ZhihuAdapter extends BaseCompatAdapter<ZhihuItemBean,BaseViewHolder> {

    public ZhihuAdapter(@LayoutRes int layoutResId){
        super(layoutResId);
    }

    public ZhihuAdapter(@LayoutRes int layoutResId, @Nullable List<ZhihuItemBean> data){
        super(layoutResId, data);
    }

    public ZhihuAdapter(@Nullable List<ZhihuItemBean> data){
        super(data);
    }

    @Override
    public void convert(final BaseViewHolder helper,ZhihuItemBean item){
        //可以设置数据库来检测是否曾经读过，如果读过，怎文字显示变色
        //但我没设置这个功能

        helper.setText(R.id.tv_item_title,item.getTitle());
        Glide.with(mContext).load(item.getImage()[0]).crossFade().into((ImageView)helper.getView(R.id.iv_item_image));
    }
}
