package zixun.xcb.example.zixun.factory.main;

import io.reactivex.Observable;
import zixun.xcb.example.zixun.model.bean.zhihu.ZhihuDetailBean;
import zixun.xcb.example.zixun.presenter.main.BaseWebViewLoadPresenter;

public interface IZhihuDetail {
    abstract class ZhihuDeatilPresenter extends BaseWebViewLoadPresenter<IZhihuDetailModel,IZhihuDetailView> {

        /*
            加载知乎日报的详情
         */
        public abstract void loadDailyDetail(String id);
    }

    interface IZhihuDetailModel extends IBaseWebViewLoad.IBaseWebViewLoadModel{
        /*
           获取日报详情

           @param id 日报 id
           @param Observable
         */
        Observable<ZhihuDetailBean> getDailyDetail(String id);
    }

    interface IZhihuDetailView extends IBaseWebViewLoad.IBaseWebViewLoadView{
        /*
          显示日报详细内容

          @param bean ZhihuDetailBean
         */
        void showDailyDetail(ZhihuDetailBean bean);
    }
}
