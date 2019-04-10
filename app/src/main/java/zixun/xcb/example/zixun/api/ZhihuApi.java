package zixun.xcb.example.zixun.api;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;
import zixun.xcb.example.zixun.model.bean.zhihu.ZhihuDetailBean;
import zixun.xcb.example.zixun.model.bean.zhihu.ZhihuItemBean;
import zixun.xcb.example.zixun.model.bean.zhihu.ZhihuListBean;

public interface ZhihuApi {

    public final String HOST = "http://news-at.zhihu.com";

    @GET("/api/4/news/latest")
    Observable<ZhihuListBean> getLastDailyList();

    @GET("/api/4/news/before/{date}")
    Observable<ZhihuListBean> getDailyListWithDate(@Path("date") String date);

    @GET("/api/4/news/{id}")
    Observable<ZhihuDetailBean> getZhihuDetail(@Path("id") String id);


}
