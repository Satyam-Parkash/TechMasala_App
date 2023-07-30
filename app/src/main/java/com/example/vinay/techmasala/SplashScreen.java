package com.example.vinay.techmasala;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import com.thefinestartist.finestwebview.FinestWebView;

public class SplashScreen extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 7000;
    private LinearLayout progress;
    private ProgressWheel progressWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progress = (LinearLayout) findViewById(R.id.progress);
        progressWheel = (ProgressWheel) findViewById(R.id.progressWheel);
        progress.setVisibility(View.VISIBLE);
        progressWheel.spin();
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {


                if (isNetworkAvailable()) {
                    progress.setVisibility(View.GONE);

                    progressWheel.stopSpinning();


                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
              finish();
                 //   new FinestWebView.Builder(SplashScreen.this).showIconMenu(false).showUrl(false).webViewAllowUniversalAccessFromFileURLs(true).showIconBack(false).showIconClose(false).showIconForward(false).show(getString(R.string.url));

                } else {
                    progress.setVisibility(View.GONE);
                    progressWheel.stopSpinning();

                    showAlert(SplashScreen.this, "Please check your Internet Connection", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                }
                // This method will be executed once the timer is over
                // Start your app main activity
//                Intent i = new Intent(SplashScreen.this, MainActivity.class);
//                startActivity(i);

                // close this activity

            }

        }, SPLASH_TIME_OUT);


    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public static void showAlert(final Context ctx, final String message, final DialogInterface.OnClickListener positiveClick) {
        final AlertDialog alert;
        final AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final Intent intent = new Intent(Intent.ACTION_MAIN, null);
                        intent.addCategory(Intent.CATEGORY_LAUNCHER);
                        final ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
                        intent.setComponent(cn);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ctx.startActivity( intent);}
                });

        alert = builder.create();

        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {

                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            }
        });
        alert.show();

    }

    public static void   openWifiSettings(){


    }
}
