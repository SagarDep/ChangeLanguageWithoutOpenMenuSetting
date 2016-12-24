package com.putuguna.changelanguagewithoutopensetting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnNext;
    private TextView tvText;


    private Spinner spinnerLanguage;
    private Locale mLocale;
    private Configuration config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText = (TextView) findViewById(R.id.txt_change_language);
        btnNext = (Button) findViewById(R.id.btn_next);
        spinnerLanguage = (Spinner) findViewById(R.id.spinner);
        btnNext.setOnClickListener(this);


        //spinner onItemSelectedListener
        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

                if(i==0){
                }else if(i==1){
                    config = getBaseContext().getResources().getConfiguration();
                    changeLanguage("id");

                }else if(i==2){
                    config = getBaseContext().getResources().getConfiguration();
                    changeLanguage("en");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /**
     * this method used to change locale on apps
     * @param lang
     */
    private void changeLanguage(String lang){
        mLocale = new Locale(lang);
        Locale.setDefault(mLocale);
        config.locale = mLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        Intent intent = new Intent(MainActivity.this, MainActivity.class); //pasang class itu sendiri
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(0,0);

        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("myLanguage", lang);
        editor.commit();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale.setDefault(mLocale);
        config.locale = mLocale;
        super.onConfigurationChanged(newConfig);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id==R.id.btn_next){
            Intent intents = new Intent(this, SecondActivity.class);
            intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intents);
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent a = new Intent(this,BeforeMainActivity.class);
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}
