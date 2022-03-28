package com.getwemap.livemap.sdk.sample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.getwemap.livemap.sdk.Livemap;
import com.getwemap.livemap.sdk.callbacks.ActionButtonClickListener;
import com.getwemap.livemap.sdk.model.Event;
import com.getwemap.livemap.sdk.model.Pinpoint;
import com.getwemap.livemap.sdkrg.LivemapRGView;
import com.getwemap.livemap.sdkrg.OnLivemapRGReadyCallback;
import com.getwemap.livemap.sdkrg.RGInterface;

public class LivemapSampleActivity extends Activity implements OnLivemapRGReadyCallback {

    private LivemapRGView mLivemapView;
    protected Logger mLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLogger = new Logger(this);

        setContentView(R.layout.activity_livemap);

        mLivemapView = findViewById(R.id.livemap);
        mLivemapView.getLivemapRGAsync(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        mLivemapView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onLivemapRGReady(Livemap livemap, RGInterface rgInterface) {

        rgInterface.addBookEventClickedListener((event) -> mLogger.log("BookEvent clicked: " + event.getId()));

        livemap.addActionButtonClickedListener(new ActionButtonClickListener() {
            @Override
            public void onPinpointActionClicked(Pinpoint pinpoint, String actionName) {
                if (actionName.equals(ActionName.DIRECTIONS.toString())) {
                    mLogger.log("GoTo Pinpoint clicked: " + pinpoint.getId());
                }
            }

            @Override
            public void onEventActionClicked(Event event, String actionName) {
                // Do nothing
            }
        });

        livemap.enableAnalytics();
        new Handler().postDelayed(livemap::disableAnalytics, 10000);

    }
}

