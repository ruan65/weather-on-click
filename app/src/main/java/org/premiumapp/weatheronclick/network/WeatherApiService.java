package org.premiumapp.weatheronclick.network;

import org.premiumapp.weatheronclick.model.WeatherOneDay;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface WeatherApiService {

    @GET("weather")
    Call<WeatherOneDay> oneDayForecast(
            @Query("APPID") String apiKey,
            @Query("units") String units,
            @Query("lat") Double lat,
            @Query("lon") Double lon
    );
}
