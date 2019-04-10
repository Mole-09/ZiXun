package zixun.xcb.example.zixun.model.bean.zhihu;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ZhihuListBean {

    @SerializedName("date")
    private String date;
    @SerializedName("top_stories")
    private ArrayList<ZhihuItemBean> mZhihuItems;
    @SerializedName("stories")
    private ArrayList<ZhihuItemBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public ArrayList<ZhihuItemBean> getmZhihuItems() {
        return mZhihuItems;
    }

    public void setmZhihuItems(ArrayList<ZhihuItemBean> mZhihuItems){
        this.mZhihuItems = mZhihuItems;
    }

    public ArrayList<ZhihuItemBean> getStories() {
        return stories;
    }

    public void setStories(ArrayList<ZhihuItemBean> stories){
        this.stories =stories;
    }

    @Override
    public String toString(){
        return "ZhihuListBean{"+
                "date='"+date+'\''+",mZhihuItems="+mZhihuItems+",stories="+stories+'}';
    }
}
