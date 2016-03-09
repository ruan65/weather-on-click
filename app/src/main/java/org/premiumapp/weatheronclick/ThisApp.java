package org.premiumapp.weatheronclick;

import android.app.Application;
import android.content.Context;

import org.premiumapp.weatheronclick.rx.RxBus;

import timber.log.Timber;

public class ThisApp extends Application {

    private RxBus appBus = null;

    public static ThisApp get(Context ctx) {
        return (ThisApp) ctx.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) +
                            ":timber: line=" + element.getLineNumber() +
                            " method: " + element.getMethodName();
                }
            });
        }
    }

    public RxBus appBus() {
        if (appBus == null) appBus = new RxBus();
        return appBus;
    }
}
