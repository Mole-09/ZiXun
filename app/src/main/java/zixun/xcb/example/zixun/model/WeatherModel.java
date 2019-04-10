package zixun.xcb.example.zixun.model;

import okhttp3.OkHttpClient;
import zixun.xcb.example.zixun.entity.WeatherBean;
import zixun.xcb.example.zixun.utils.OkhttpUtils;

/*
  Model层接口
 */
public class WeatherModel implements WeatherModelImpl{
    @Override
    public void loadWeather(String url,final LoadListener loadListener){
        OkhttpUtils.ResultCallback resultCallback = new OkhttpUtils.ResultCallback() {
            @Override
            public void getWeather(WeatherBean weatherBean) {
                loadListener.onSuccess(weatherBean);
            }

            @Override
            public void onFailure(Exception e) {

                loadListener.onFailure(e);
            }
        };
        OkhttpUtils.getResultCallback(url,resultCallback);
    }

}
