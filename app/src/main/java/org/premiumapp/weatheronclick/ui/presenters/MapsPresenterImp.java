package org.premiumapp.weatheronclick.ui.presenters;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import org.premiumapp.weatheronclick.BuildConfig;
import org.premiumapp.weatheronclick.ThisApp;
import org.premiumapp.weatheronclick.model.WeatherOneDay;
import org.premiumapp.weatheronclick.network.WeatherApiService;
import org.premiumapp.weatheronclick.rx.RxBus;
import org.premiumapp.weatheronclick.ui.activities.MapsView;
import org.premiumapp.weatheronclick.utils.Cv;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import rx.observables.ConnectableObservable;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class MapsPresenterImp implements MapsPresenterType {

    private MapsView view;
    private RxBus appBus;
    private Context ctx;
    private TextView display;
    private ImageView iconIv;
    private CompositeSubscription subscription;
    private WeatherApiService weatherApiService;
    private String units = Cv.METRIC;
    private static final String API_W = BuildConfig.API_W;

    public MapsPresenterImp(MapsView v) {
        ctx = (Context) v;
        appBus = ThisApp.get(ctx).appBus();
        view = v;
        display = view.getDisplay();
        iconIv = view.getIconIv();

        weatherApiService = new Retrofit.Builder()
                .baseUrl(Cv.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiService.class);
    }

    @Override
    public void onResume() {
        subscription = new CompositeSubscription();
        ConnectableObservable<Object> publisher = appBus.toObservable().publish();

        subscription.add(publisher.subscribe(this::handleEvent));
        subscription.add(publisher.connect());
    }

    @Override
    public void onPause() {
        subscription.clear();
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void setUnits(String units) {
        this.units = units;
        display.setText("");
        iconIv.setImageDrawable(null);
    }

    @Override
    public void onMapClick(LatLng latLng) {

        Call<WeatherOneDay> weatherOneDayCall = weatherApiService.oneDayForecast(API_W, units,
                latLng.latitude, latLng.longitude);

        new Thread(() -> {
            weatherOneDayCall.enqueue(new Callback<WeatherOneDay>() {
                @Override
                public void onResponse(Response<WeatherOneDay> response, Retrofit retrofit) {
                    WeatherOneDay weather = response.body();
                    appBus.send(weather);
                    Timber.i(weather.toString());
                }

                @Override
                public void onFailure(Throwable t) {
                    Timber.i(t.getMessage());
                }
            });
        }).start();
    }

    private void handleEvent(Object event) {

        if (event instanceof WeatherOneDay) {

            WeatherOneDay  weather = (WeatherOneDay) event;
            WeatherOneDay.Weather wth = weather.weather[0];

            String data = String.format("%.1f%s %s", weather.main.temp,
                    units.equals(Cv.METRIC) ? Cv.C : Cv.F, weather.name);
            display.setText(data);

            Picasso.with(ctx).load(String.format(Cv.ICON_URL, wth.icon.substring(0,2))).into(iconIv);
        }
    }
}
