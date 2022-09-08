package com.affinity.rappo.ui_activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.affinity.rappo.R;
import com.affinity.rappo.model.Constant;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                SharedPreferences prefs = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE);
                if(prefs.getBoolean(Constant.KEY_PIN_LOGGED_IN, false)) {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();

                }else {
                    Intent intent=new Intent(SplashScreen.this, PinEntryActivity.class);
                    startActivity(intent);
                }

            }
        },SPLASH_TIME_OUT);
    }
}
