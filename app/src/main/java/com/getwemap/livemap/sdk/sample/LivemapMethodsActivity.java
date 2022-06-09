package com.getwemap.livemap.sdk.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.getwemap.livemap.sdk.Livemap;
import com.getwemap.livemap.sdk.LivemapView;
import com.getwemap.livemap.sdk.callback.LivemapReadyCallback;
import com.getwemap.livemap.sdk.model.Coordinates;
import com.getwemap.livemap.sdk.options.PolylineOptions;

import java.util.Arrays;


public class LivemapMethodsActivity extends Activity implements LivemapReadyCallback {

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
                new Coordinates(43.609395, 3.884215), 5),
                3000
        );

        // Programmatically open / close pinpoints and events
        new Handler().postDelayed(() -> livemap.openPinpoint(35090408), 6000);
        new Handler().postDelayed(livemap::closePinpoint, 8000);
        new Handler().postDelayed(() -> livemap.openEvent(10761031), 9000);
        new Handler().postDelayed(livemap::closeEvent, 11000);

        // Navigation
        livemap.addGuidingStartedListener(() -> mLogger.log("Guiding started"));
        livemap.addGuidingUpdatedListener(r -> mLogger.log("Guiding update: remaining " + r + " m"));
        livemap.addGuidingStoppedListener(() -> mLogger.log("Guiding stopped"));
        new Handler().postDelayed(() -> livemap.navigateToPinpoint(35090408), 12000);
        new Handler().postDelayed(livemap::stopNavigation, 22000);

        // Draw a polyline
        livemap.drawPolyline(
                Arrays.asList(new Coordinates(35, -30), new Coordinates(45, 4)),
                new PolylineOptions(),
                id -> new Handler().postDelayed(() -> livemap.removePolyline(id), 10000)
        );

        // // Add custom pinpoints
        // livemap.setPinpoints(
        //         Arrays.asList(
        //                 new Pinpoint(0, "Foo", new Coordinates(45, 5)),
        //                 new Pinpoint(1, "Foobar", new Coordinates(46, 3))
        //         ),
        //         boundingBox -> mLogger.log("pinpoints added [bounds: " + boundingBox + "]")
        // );

        // // Add custom events
        // EventDate[] dates = new EventDate[]{
        //         new EventDate("2018-09-15T08:00:00.000Z", "2018-09-16T08:00:00.000Z")
        // };
        // Pinpoint pinpoint = new Pinpoint(2, "Wemap Office", new Coordinates(43.609138, 3.884193));
        // livemap.setEvents(Collections.singletonList(new Event(1, "Event", pinpoint, dates)),
        //         boundingBox -> mLogger.log("events added [bounds: " + boundingBox + "]"));

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

        // // Find the nearest pinpoints from a point.
        // livemap.findNearestPinpoints(
        //         new Coordinates(45, 4),
        //         pinpoints -> mLogger.log("Nearest pinpoints: " + Arrays.toString(pinpoints))
        // );

        // livemap.getCenter(center -> mLogger.log("Center: " + center));
        // livemap.getZoom(zoom -> mLogger.log("Zoom: " + zoom));

        // livemap.openList(1234);
        // livemap.closeList();

        // livemap.closePopin();

        // // Fit the map on given bounds.
        // livemap.fitBounds(new BoundingBox(0, 43, 10, 48));

        // // Get User/Device position / attitude
        // new Handler().postDelayed(
        //         () -> livemap.getUserLocation(l -> mLogger.log("UserLocation: " + l)),
        //         5000);
        // new Handler().postDelayed(
        //         () -> livemap.getDeviceAttitude(att -> mLogger.log("Device attitude: " + att)),
        //         5000);

        // // Use custom User/Device position / attitude
        // livemap.disablePositioningSystem();
        // livemap.setUserLocation(new UserLocation(43.609395, 3.884215, null, 50));
        // livemap.setDeviceAttitude(new Attitude(new float[]{1, 0, 0, 0}));
    }
}

