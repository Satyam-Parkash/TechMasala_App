package com.example.vinay.techmasala;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.thefinestartist.finestwebview.FinestWebView;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private boolean backButtonPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        setTitle("Tech Masala");
        new FinestWebView.Builder(MainActivity.this).showIconMenu(false).showUrl(false).webViewAllowUniversalAccessFromFileURLs(true).showIconBack(false).showIconClose(false).showIconForward(false).show(getString(R.string.url));
    }


    @Override
    public void onBackPressed() {
        backButtonHandler();
        // super.onBackPressed();

    }

    public void backButtonHandler() {
        final AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(

                MainActivity.this);

        // Setting Dialog Title

        builder.setTitle("Leave application?");

        // Setting Dialog Message

        builder.setMessage("Are you sure you want to leave the application?");

        // Setting Icon to Dialog

        builder.setIcon(R.drawable.ilove);

        // Setting Positive "Yes" Button

        builder.setPositiveButton("YES",

                new DialogInterface.OnClickListener() {

                    @SuppressLint("NewApi")
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }

                });

        // Setting Negative "NO" Button

        builder.setNegativeButton("NO",

                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        // Write your code here to invoke NO event
                        new FinestWebView.Builder(MainActivity.this).showIconMenu(false).showUrl(false).webViewAllowUniversalAccessFromFileURLs(true).showIconBack(false).showIconClose(false).showIconForward(false).show(getString(R.string.url));

                    }

                });
        alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {

                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLUE);
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
            }
        });
        alertDialog.show();


        // Showing Alert Message


    }
}
