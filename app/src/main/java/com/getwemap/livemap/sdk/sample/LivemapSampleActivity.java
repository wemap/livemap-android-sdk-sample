package com.getwemap.livemap.sdk.sample;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.getwemap.livemap.sdk.Livemap;
import com.getwemap.livemap.sdk.LivemapView;
import com.getwemap.livemap.sdk.OnLivemapReadyCallback;

import java.util.ArrayList;

public class LivemapSampleActivity extends Activity implements OnLivemapReadyCallback {

    private LivemapView mLivemapView;
    protected Logger mLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLogger = new Logger(this);

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

        livemap.addPinpointOpenListener(pinpoint -> mLogger.log("Pinpoint open: " + pinpoint.getId()));
        livemap.addPinpointCloseListener(() -> mLogger.log("Pinpoint close"));

        livemap.addEventOpenListener(event -> mLogger.log("Event open: " + event.getId()));
        livemap.addEventCloseListener(() -> mLogger.log("Event close"));

        livemap.addGuidingStartedListener(() -> mLogger.log("Guiding started"));
        livemap.addGuidingStoppedListener(() -> mLogger.log("Guiding stopped"));

        // livemap.navigateToPinpoint(31604315);

        livemap.aroundMe();

        // add 2 points in Montpellier
        ArrayList<Integer> sourceLists = new ArrayList<Integer>();
        sourceLists.add(74878);
        livemap.setSourceLists(sourceLists);

        livemap.addMapClickListener(point -> mLogger.log("Map click: " + point.toString()));
        livemap.addMapLongClickListener(point -> mLogger.log("Map long click: " + point.toString()));
        livemap.addMapMovedListener(mapMoved -> {
            mLogger.log("Map moved: " + mapMoved.toString());
        });
    }
}

