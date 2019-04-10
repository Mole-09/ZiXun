package zixun.xcb.example.zixun.presenter;

import zixun.xcb.example.zixun.entity.WeatherBean;
import zixun.xcb.example.zixun.model.LoadListener;
import zixun.xcb.example.zixun.model.WeatherModel;
import zixun.xcb.example.zixun.model.WeatherModelImpl;
import zixun.xcb.example.zixun.view.WeatherViewImpl;

public class WeatherPresenter implements WeatherPresenterImpl,LoadListener{

    private String url = "https://www.sojson.com/open/api/weather/json.shtml?city=";

    private WeatherViewImpl weatherView;
    private WeatherModelImpl weatherModel;

    public WeatherPresenter(WeatherViewImpl weatherView){
        this.weatherView = weatherView;
        this.weatherModel = new WeatherModel();
    }

    @Override
    public void loadWeather(String city){

        weatherModel.loadWeather(url+city,this);
    }

    @Override
    public void onSuccess(WeatherBean weatherBean){

        weatherView.showWeatherData(weatherBean);
    }

    @Override
    public void onFailure(Exception e){

    }
}
