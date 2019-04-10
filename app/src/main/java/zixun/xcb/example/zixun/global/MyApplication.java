package zixun.xcb.example.zixun.global;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class MyApplication extends Application{

    @Override
    public void onCreate(){
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        //开启debug会影响性能

        //全局默认信任所以https域名 或 仅添加信任的https域名
        //使用RequestParams#setHostnameVerifier(...)方法可设置单次请求的域名校验
        x.Ext.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
    }
}
