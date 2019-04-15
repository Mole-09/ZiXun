package zixun.xcb.example.sdk.base;

import android.support.annotation.NonNull;

import zixun.xcb.example.sdk.RxManager;

public abstract class BasePresenter<M,V> {

    protected M mIModel;
    protected V mIView;
    protected RxManager mRxManager = new RxManager();

    /*
        返回presenter想持有的Model引用

        @return presenter 持有的Model引用
     */
    protected abstract M getmIModel();

    /*
         绑定IModel 和 IView 的引用

         @param v view
     */
    public void attachMV(@NonNull V v){
        this.mIModel = getmIModel();
        this.mIView = v;
        this.onStart();
    }

    /*
          解绑IModel 和 IView
     */
    public void deatchMV(){
        mRxManager.unSubscribe();
        mIView = null;
        mIModel = null;
    }

    /*
         IView 和 IModel 绑定完成后立即执行

         实现类实现绑定完成后的逻辑，例如数据初始化等，界面初始化，更新等
     */
    public abstract void onStart();
}
