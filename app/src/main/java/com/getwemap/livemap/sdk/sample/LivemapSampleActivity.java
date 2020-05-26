package com.getwemap.livemap.sdk.sample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.getwemap.livemap.sdk.Livemap;
import com.getwemap.livemap.sdk.model.LatLngAlt;
import com.getwemap.livemap.sdkrg.LivemapRGView;
import com.getwemap.livemap.sdkrg.OnLivemapRGReadyCallback;
import com.getwemap.livemap.sdkrg.RGInterface;

import org.json.JSONException;

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

        /*
         * Workaround because centerTo does not work after waitForReady
         * @see https://getwemap.atlassian.net/browse/WEEMM-1915
         */
        new Handler().postDelayed(() -> {
            livemap.centerTo(new LatLngAlt(48.8471, 2.2486), 17);
            mLogger.log("Centered map to: 48.8471, 2.2486");
        }, 500);

        livemap.addPinpointOpenListener(pinpoint -> {
            mLogger.log("Pinpoint open: " + pinpoint.getId());
            try {
                if (pinpoint.getExternalData().has("prismic_id")) {
                    mLogger.log("Prismic id: " + pinpoint.getExternalData().getString("prismic_id"));
                }
            } catch (JSONException ignored) {
            }
        });
        livemap.addPinpointCloseListener(() -> mLogger.log("Pinpoint close"));

        livemap.addEventOpenListener(event -> mLogger.log("Event open: " + event.getId()));
        livemap.addEventCloseListener(() -> mLogger.log("Event close"));

        livemap.addGuidingStartedListener(() -> mLogger.log("Guiding started"));
        livemap.addGuidingStoppedListener(() -> mLogger.log("Guiding stopped"));

        rgInterface.addBookEventClickedListener((eventId) -> mLogger.log("BookEvent clicked: " + eventId));
        rgInterface.addGoToPinpointClickedListener((pinpointId) -> mLogger.log("GoTo Pinpoint clicked: " + pinpointId));

        // livemap.navigateToPinpoint(31604315);

    }
}

