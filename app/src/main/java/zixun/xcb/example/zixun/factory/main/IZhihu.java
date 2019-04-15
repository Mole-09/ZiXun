package zixun.xcb.example.zixun.factory.main;

import io.reactivex.Observable;

import zixun.xcb.example.zixun.model.bean.zhihu.ZhihuItemBean;
import zixun.xcb.example.zixun.model.bean.zhihu.ZhihuListBean;

public interface IZhihu {

    abstract class ZhihuPresenter extends IBaseTabs.BaseTabsPresenter
    <IZhihuModel,IZhihuView,ZhihuItemBean> {}

    interface IZhihuModel extends IBaseTabs.IBaseTabsModel{
        /*
            根据日期获取日报list --> 20180409

            @param date 日期
            @return Observable
         */
        Observable<ZhihuListBean> getDailyList(String date);

        /*
            获取日期list

            @return Observable
         */
        Observable<ZhihuListBean> getDailyList();
    }

    interface IZhihuView extends IBaseTabs.IBaseTabsView<ZhihuItemBean>{}
}
