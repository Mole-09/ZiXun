package zixun.xcb.example.sdk;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/*
    用于管理Rxjava 注册订阅和取消订阅
 */
public class RxManager {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    //管理订阅者

    public void register(Disposable d){
        compositeDisposable.add(d);
    }

    public void unSubscribe(){
        compositeDisposable.dispose();
        //取消订阅
    }
}
