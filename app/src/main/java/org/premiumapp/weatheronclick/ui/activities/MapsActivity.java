package org.premiumapp.weatheronclick.ui.activities;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.premiumapp.weatheronclick.R;
import org.premiumapp.weatheronclick.ui.presenters.MapsPresenterImp;
import org.premiumapp.weatheronclick.ui.presenters.MapsPresenterType;
import org.premiumapp.weatheronclick.utils.Cv;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapsActivity extends FragmentActivity implements MapsView,
        OnMapReadyCallback, OnMapClickListener {

    private MapsPresenterType presenter;

    @Bind(R.id.tv_display) TextView tvDisplay;
    @Bind(R.id.icon_weather_cond) ImageView ivIcon;
    @Bind(R.id.menu_more) ImageView menuMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .getMapAsync(this);

        presenter = new MapsPresenterImp(this);
        registerForContextMenu(menuMore);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {

        presenter.onMapClick(latLng);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        String unit = PreferenceManager.getDefaultSharedPreferences(this)
                .getString(Cv.KEY_UNITS, Cv.C);
        menu.add(unit.equals(Cv.C) ? Cv.F : Cv.C);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        boolean celsius = item.getTitle().equals(Cv.C);
        if (celsius) {
            item.setTitle(Cv.F);
        } else {
            item.setTitle(Cv.C);
        }
        presenter.setUnits(celsius ? Cv.METRIC : Cv.IMPERIAL);
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putString(Cv.KEY_UNITS, celsius ? Cv.C : Cv.F).apply();
        return super.onContextItemSelected(item);
    }

    // Presenter boilerplate
    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public TextView getDisplay() {
        return tvDisplay;
    }

    @Override
    public ImageView getIconIv() {
        return ivIcon;
    }

    @OnClick(R.id.menu_more)
    public void menu(View v) {
        openContextMenu(v);
    }
}
