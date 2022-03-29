package com.getwemap.livemap.sdk.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.getwemap.livemap.sdk.Livemap;
import com.getwemap.livemap.sdk.LivemapView;
import com.getwemap.livemap.sdk.OnLivemapReadyCallback;
import com.getwemap.livemap.sdk.model.LatLngAlt;
import com.getwemap.livemap.sdk.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;


public class OtherSdkMethodsActivity extends Activity implements OnLivemapReadyCallback {

    private LivemapView mLivemapView;
    private Logger mLogger;

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mLivemapView.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onLivemapReady(Livemap livemap) {
        
        // Center the map to a specific location and zoom
        new Handler().postDelayed(() -> livemap.centerTo(
                new LatLngAlt(43.609395, 3.884215), 5),
                3000
        );

        // Programmatically open / close pinpoints and events
        new Handler().postDelayed(() -> livemap.openPinpoint(35090408), 6000);
        new Handler().postDelayed(livemap::closePinpoint, 8000);
        new Handler().postDelayed(() -> livemap.openEvent(10761031), 9000);
        new Handler().postDelayed(livemap::closeEvent, 11000);

        // Navigation
        livemap.addGuidingStartedListener(() -> mLogger.log("Guiding started"));
        livemap.addGuidingStoppedListener(() -> mLogger.log("Guiding stopped"));
        new Handler().postDelayed(() -> livemap.navigateToPinpoint(35090408), 12000);
        new Handler().postDelayed(livemap::stopNavigation, 22000);

        // Draw a polyline
        livemap.drawPolyline(
                Arrays.asList(new LatLngAlt(35, -30), new LatLngAlt(45, 4)),
                new PolylineOptions(),
                id -> new Handler().postDelayed(() -> livemap.removePolyline(id), 10000)
        );

        // // Filtering the contents
        // livemap.setFilters(new Filters(
        //         "2017-02-01",
        //         "2017-02-05",
        //         "arts décoratifs",
        //         new String[]{"monument-historique", "musee-de-france"}
        // ));

        // // Center the map on the user position
        // livemap.aroundMe();

        // // Set a specific list by id
        // livemap.setSourceLists(Collections.singletonList(74878));

        // // Enable / disable analytics
        // livemap.enableAnalytics();
        // livemap.disableAnalytics();

        // // Show / hide sidebar
        // livemap.enableSidebar();
        // livemap.disableSidebar();

        // // User sign in / sign out
        // livemap.signInByToken("myToken");
        // livemap.signOut();

    }
}

