package com.getwemap.livemap.sdk.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.getwemap.livemap.sdk.Livemap;
import com.getwemap.livemap.sdkpositioning.LivemapPositioningInterface;
import com.getwemap.livemap.sdkpositioning.LivemapPositioningView;
import com.getwemap.livemap.sdkpositioning.OnLivemapPositioningReadyCallback;

public class PositioningSdkActivity extends Activity implements OnLivemapPositioningReadyCallback {

    private LivemapPositioningView mLivemapView;
    private Button mButton;
    private Logger mLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLogger = new Logger(this);

        setContentView(R.layout.activity_positioning_sdk);

        mLivemapView = findViewById(R.id.livemap);
        mLivemapView.getLivemapPositioningAsync(this);

        mButton = findViewById(R.id.button);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        mLivemapView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onLivemapReady(Livemap livemap, LivemapPositioningInterface positioningInterface) {
        mButton.setOnClickListener((e) -> positioningInterface.start());
        mButton.setEnabled(true);

        livemap.addUserLocationUpdatedListener(l ->  mLogger.log("onUserLocationUpdated: " + l.toString()));
        livemap.addDeviceAttitudeUpdatedListener(a ->  mLogger.log("onDeviceAttitudeUpdated: " + a.toString()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mLivemapView.onActivityResult(requestCode, resultCode, data);
    }

}

