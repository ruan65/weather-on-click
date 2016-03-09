package org.premiumapp.weatheronclick.ui.presenters;

import com.google.android.gms.maps.model.LatLng;

public interface MapsPresenterType {

    void onResume();

    void onPause();

    void onDestroy();

    void onMapClick(LatLng coords);

    void setUnits(String units);
}
