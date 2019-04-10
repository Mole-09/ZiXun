package zixun.xcb.example.zixun.model;

import zixun.xcb.example.zixun.entity.WeatherBean;

/*
 状态监听器
 */
public interface LoadListener {
    void onSuccess(WeatherBean bean);
    void onFailure(Exception e);
}
