package com.putuguna.changelanguagewithoutopensetting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Locale;

public class SplashScreenActivity extends AppCompatActivity {

    /**
     * you can change this timer
     * 2000 = 2 second
     */
    public static final int SPLASH_DELAY = 2000;
    private ProgressBar progressBar;

    private Locale mLocale;
    private Configuration configuration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);

        getCurrentLocale();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                progressBar.setVisibility(View.GONE);
                Intent intent  = new Intent(SplashScreenActivity.this, BeforeMainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);;
                finish();
            }
        }, SPLASH_DELAY);
    }

    /**
     * this method used to get current locale
     */
    private void getCurrentLocale(){
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String locale = sharedPreferences.getString("myLanguage",null);
        if(locale==null){
        }else {
            configuration = getBaseContext().getResources().getConfiguration();
            mLocale = new Locale(locale);
            Locale.setDefault(mLocale);
            configuration.locale = mLocale;
            getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Configuration config = getResources().getConfiguration();
        Locale.setDefault(mLocale);
        config.locale = mLocale;
        super.onConfigurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }
}
