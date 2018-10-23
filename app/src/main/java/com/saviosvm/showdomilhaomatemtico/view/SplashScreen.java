package com.saviosvm.showdomilhaomatemtico.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.saviosvm.showdomilhaomatemtico.AndGraph.*;

import com.saviosvm.showdomilhaomatemtico.R;

public class SplashScreen extends AppCompatActivity {

    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        AGSoundManager.init(this);
        AGSoundManager.vrMusic.loadMusic("menu.mp3", true);
        AGSoundManager.vrMusic.play();

        intent = new Intent(getApplicationContext(), Principal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 3000);


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(getBaseContext(),Principal.class));
//                finish();
//            }
//        },2000);


    }
}
