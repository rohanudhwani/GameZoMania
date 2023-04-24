package com.example.parseemailverification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintLayoutStates;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    ImageView splashImageView;
    View constraintLayoutSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        constraintLayoutSplash = findViewById(R.id.constraintLayoutSplash);

//        splashImageView = findViewById(R.id.splashImageView);

        Animation splashAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);

        constraintLayoutSplash.startAnimation(splashAnimation);

//        splashImageView.startAnimation(splashAnimation);


        Intent iHome = new Intent(SplashActivity.this, LoginActivity.class);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(iHome);
                finish();
            }
        }, 1000);




    }
}