package com.getwemap.livemap.sdk.sample;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.getwemap.livemap.sdk.Livemap;
import com.getwemap.livemap.sdk.LivemapOptions;
import com.getwemap.livemap.sdk.LivemapView;
import com.getwemap.livemap.sdk.OnLivemapReadyCallback;

public class LivemapProgrammaticalySampleActivity extends Activity implements OnLivemapReadyCallback {

    private LivemapView mLivemapView;
    protected Logger mLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLogger = new Logger(this);

        LivemapOptions livemapOptions = new LivemapOptions();
        livemapOptions.emmid = 19158;
        livemapOptions.token = "GUHTU6TYAWWQHUSR5Z5JZNMXX";

        mLivemapView = new LivemapView(this, livemapOptions);
        mLivemapView.getLivemapAsync(this);

        setContentView(mLivemapView);
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
    }
}

