package com.getwemap.livemap.sdk.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.getwemap.livemap.sdk.Livemap;
import com.getwemap.livemap.sdk.LivemapView;
import com.getwemap.livemap.sdk.OnLivemapReadyCallback;
import com.getwemap.livemap.sdk.callbacks.ActionButtonClickListener;
import com.getwemap.livemap.sdk.callbacks.ContentUpdatedListener;
import com.getwemap.livemap.sdk.model.Event;
import com.getwemap.livemap.sdk.model.Pinpoint;
import com.getwemap.livemap.sdk.model.Query;

import java.util.List;

public class EventsActivity extends Activity implements OnLivemapReadyCallback {

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
    public void onLivemapReady(Livemap livemap) {
        
        livemap.addPinpointOpenListener(pinpoint -> mLogger.log("Pinpoint open: " + pinpoint.getId()));
        livemap.addPinpointCloseListener(() -> mLogger.log("Pinpoint close"));

        livemap.addEventOpenListener(event -> mLogger.log("Event open: " + event.getId()));
        livemap.addEventCloseListener(() -> mLogger.log("Event close"));

        livemap.addMapClickListener(point -> mLogger.log("Map click: " + point.toString()));
        livemap.addMapLongClickListener(point -> mLogger.log("Map long click: " + point.toString()));
        livemap.addMapMovedListener(mapMoved -> {
            mLogger.log(mapMoved.toString());
        });

        livemap.addActionButtonClickedListener(new ActionButtonClickListener() {
            @Override
            public void onPinpointActionClicked(Pinpoint pinpoint, String actionName) {
                mLogger.log("Action '" + actionName + "' clicked for pinpoint: " + pinpoint.getId());
            }

            @Override
            public void onEventActionClicked(Event event, String actionName) {
                mLogger.log("Action '" + actionName + "' clicked for event: " + event.getId());
            }
        });

        livemap.addContentUpdatedListener(new ContentUpdatedListener() {
            @Override
            public void onPinpointsUpdated(Query query, List<Pinpoint> pinpoints) {
                mLogger.log("On Pinpoints Updated (" + pinpoints.size() + "), query: " + query.toString());
            }

            @Override
            public void onEventsUpdated(Query query, List<Event> events) {
                mLogger.log("On Events Updated (" + events.size() + "), query: " + query.toString());
            }
        });
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

}
