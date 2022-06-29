package com.getwemap.livemap.sdk.sample;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.getwemap.livemap.sdk.LivemapView;
import com.getwemap.livemap.sdk.options.LivemapOptions;

public class ProgrammaticallyViewActivity extends Activity {

    private LivemapView mLivemapView;
    protected Logger mLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLogger = new Logger(this);

        LivemapOptions livemapOptions = new LivemapOptions();
        livemapOptions.emmid = 19158;
        livemapOptions.token = "GUHTU6TYAWWQHUSR5Z5JZNMXX";
        // livemapOptions.maxbounds = new BoundingBox(-10, -10, 90, 90);
        // livemapOptions.introcardActive = false;

        mLivemapView = new LivemapView(this, livemapOptions);
        setContentView(mLivemapView);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        mLivemapView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}

