package com.getwemap.livemap.sdk.sample;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.getwemap.livemap.sdk.Livemap;
import com.getwemap.livemap.sdk.LivemapView;
import com.getwemap.livemap.sdk.OnLivemapReadyCallback;
import com.getwemap.livemap.sdk.model.Filters;
import com.getwemap.livemap.sdk.model.LatLngAlt;

public class LivemapSampleActivity extends AppCompatActivity implements OnLivemapReadyCallback {

    enum Sample {
        Map,
        PinpointEvent,
        Navigation
    }

    private final static String TAG = "WEMAP-TAG";
    private final static Sample sample = Sample.PinpointEvent;

    private LivemapView mLivemapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_livemap);

        mLivemapView = findViewById(R.id.livemap);
        mLivemapView.getLivemapAsync(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        mLivemapView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onLivemapReady(Livemap livemap) {

        if (sample == Sample.Map) {
            livemap.centerTo(new LatLngAlt(48.8, 2.3), 10);
        }
        else if(sample == Sample.PinpointEvent) {
            livemap.addPinpointOpenListener((pinpoint) -> Log.v(TAG, "Pinpoint open: " + pinpoint.toString()));
            livemap.addPinpointCloseListener(() -> Log.v(TAG, "Pinpoint close"));

            livemap.addEventOpenListener((event) -> Log.v(TAG, "Event open: " + event.toString()));
            livemap.addEventCloseListener(() -> Log.v(TAG, "Event close"));

            livemap.setFilters(new Filters("2019-09-21", "2019-09-22", "", new String[]{}));
        }
        else if (sample == Sample.Navigation) {
            livemap.navigateToPinpoint(26347232);
            livemap.addGuidingStartedListener(() -> Log.v(TAG, "Guiding started"));
            livemap.addGuidingStoppedListener(() -> Log.v(TAG, "Guiding stopped"));
        }

    }
}

