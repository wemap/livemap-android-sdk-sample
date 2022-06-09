package com.getwemap.livemap.sdk.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.getwemap.livemap.sdk.Livemap;
import com.getwemap.livemap.sdk.LivemapView;
import com.getwemap.livemap.sdk.callback.LivemapReadyCallback;


public class HelloWorldActivity extends Activity implements LivemapReadyCallback {

    private LivemapView mLivemapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_livemap);

        mLivemapView = findViewById(R.id.livemap);
        mLivemapView.getLivemapAsync(this);
    }


    @Override
    public void onLivemapReady(Livemap livemap) {
        // TODO: Do what you want with livemap object
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

