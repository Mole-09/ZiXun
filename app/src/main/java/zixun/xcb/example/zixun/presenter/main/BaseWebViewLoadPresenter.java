package zixun.xcb.example.zixun.presenter.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;

import zixun.xcb.example.zixun.factory.main.IBaseWebViewLoad;

public abstract class BaseWebViewLoadPresenter<M extends IBaseWebViewLoad.IBaseWebViewLoadModel,V extends IBaseWebViewLoad.IBaseWebViewLoadView>
             extends IBaseWebViewLoad.BaseWebViewLoadPresenter<M,V>{


    /*
        壁纸保存功能

        还没做
     */
    @Override
    public  void saveImageClicked(final FragmentActivity activity,final String imgUrl){

    }

    /*
        壁纸小窗浏览

        没做
     */
    @Override
    public void gotoImageBrowseClicked(String imgUrl){

    }


    /*
          图片长按处理

          没做
     */
    @Override
    public void imageLongClicked(WebView.HitTestResult hitTestResult){

    }


}
