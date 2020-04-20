package com.getwemap.livemap.sdk.sample;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Logger {

    private final Context mContext;

    public Logger(Context context) {
        mContext = context;
    }

    public void log(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        Log.v("Livemap-SDK", msg);
    }
}
