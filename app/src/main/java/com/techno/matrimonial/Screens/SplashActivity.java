package com.techno.matrimonial.Screens;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.techno.matrimonial.Global.AppDialog;
import com.techno.matrimonial.Global.Global;
import com.techno.matrimonial.R;

/**
 * Created by arbaz on 19/7/16.
 */
public class SplashActivity extends AppCompatActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (Global.isNetworkAvailable(SplashActivity.this)) {
                    if (Global.getPreference("IsLogin", false)) {
                        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                        Global.activityTransition(SplashActivity.this);
                    } else {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity   .class);
                        startActivity(intent);
                        finish();
                        Global.activityTransition(SplashActivity.this);
                    }

                } else {
                    AppDialog.noNetworkDialog(SplashActivity.this, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();


                        }
                    });
                }

            }
        }, 3000);
    }
}
