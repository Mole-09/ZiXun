package zixun.xcb.example.zixun.utils;

import android.service.carrier.CarrierMessagingService;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import zixun.xcb.example.zixun.entity.WeatherBean;

public class OkhttpUtils {

    static String res = null;
    private static OkhttpUtils okhttpUtils;

    private synchronized static  OkhttpUtils getInstance(){
        if(okhttpUtils == null){
            okhttpUtils = new OkhttpUtils();
        }
        return  okhttpUtils;
    }

    public interface ResultCallback{
        void getWeather(WeatherBean weatherBean);
        void onFailure(Exception e);
    }
    public static void getResultCallback(String url,final ResultCallback resultCallback){
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(2, TimeUnit.SECONDS).build();

        final Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(resultCallback != null){
                    resultCallback.onFailure(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                res = response.body().string();
                Log.i("res",res);
                WeatherBean bean = JsonUtils.getWeather(res);
                if(resultCallback != null){
                    resultCallback.getWeather(bean);
                }
            }
        });
    }
}
