package me.zhouzhuo810.appx.api;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import me.zhouzhuo810.appx.R;
import me.zhouzhuo810.appx.api.entity.GetWeatherList;
import me.zhouzhuo810.magpiex.utils.ApiUtil;
import me.zhouzhuo810.magpiex.utils.BaseUtil;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public class Api {
    
    public static final String URL_RETROFIT = "http://wthrcdn.etouch.cn/";
    
    public static final String DOWNLOAD_URL = "http://p2.so.qhimgs1.com/t012a3be3c0d1bb9622.jpg";
    
    private static WeatherApi weatherApi;
    
    public interface WeatherApi {
        @GET("weather_mini")
        Observable<GetWeatherList> getWeatherList(@Query("city") String city);
        
        @Streaming
        @GET
        Observable<ResponseBody> downloadUrl(@Url String url);
    }
    
    public static WeatherApi getApi() {
        if (weatherApi == null) {
            synchronized (Api.class) {
                if (weatherApi == null) {
                    weatherApi = ApiUtil.createApiWithShareNotice(
                        WeatherApi.class,
                        URL_RETROFIT,
                        20,
                        TimeUnit.SECONDS,
                        true,
                        false,
                        R.mipmap.ic_launcher,
                        "1",
                        BaseUtil.getApp().getString(R.string.app_name)
                    );
                }
            }
        }
        return weatherApi;
    }
    
    
}

