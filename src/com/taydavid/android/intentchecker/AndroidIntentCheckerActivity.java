package com.taydavid.android.intentchecker;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class AndroidIntentCheckerActivity extends Activity {
    public final static String TAG = AndroidIntentCheckerActivity.class.getSimpleName();

    static final String[] CATEGORY_INTENT_TABS = {
            "MediaIntents", "SettingsIntents", "LauncherIntents"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume called");
        super.onResume();
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart called");
        super.onStart();

    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause called");
        super.onPause();

    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop called");
        super.onStop();
    }
}
