package zixun.xcb.example.zixun.utils;

import com.google.gson.Gson;

import zixun.xcb.example.zixun.entity.WeatherBean;

public class JsonUtils {

    public static WeatherBean getWeather(String res){
        Gson gson = new Gson();
        WeatherBean weatherBean = gson.fromJson(res,WeatherBean.class);
        return weatherBean;
    }
}
