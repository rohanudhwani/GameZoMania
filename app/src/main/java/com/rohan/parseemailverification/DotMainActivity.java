package com.rohan.parseemailverification;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.installreferrer.BuildConfig;

import java.util.Random;

public class DotMainActivity extends AppCompatActivity {

    private int count = 0;
    private TextView countTextView;
    private Random random;

    private Handler handler = new Handler();
    private Runnable addDotRunnable = new Runnable() {
        @Override
        public void run() {
            addDot();
            handler.postDelayed(this, 10);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot_main);

        countTextView = findViewById(R.id.countTextView);
        random = new Random();

        timer.start();

        handler.post(addDotRunnable);
    }

    CountDownTimer timer = new CountDownTimer(30000, 100) {
        public void onTick(long millisUntilFinished) {
            TextView timerTextView = findViewById(R.id.timerTextView);
            timerTextView.setText("Time remaining: " + millisUntilFinished / 1000 + " seconds");
        }

        public void onFinish() {

            Toast.makeText(DotMainActivity.this, "Game over! Your score was: " + count, Toast.LENGTH_SHORT).show();
            handler.removeCallbacks(addDotRunnable);

            Intent intentReset = new Intent(DotMainActivity.this, DotMainActivity.class);
            intentReset.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentReset);
        }
    };



    public void onDotClick(View view) {
        count++;
        countTextView.setText(String.valueOf(count));

        ViewGroup container = findViewById(R.id.container);
        container.removeView(view);

//        ViewGroup parentView = (ViewGroup) view.getParent();
//        parentView.removeView(view);
        addDot();

    }


    private void addDot() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        int size = random.nextInt(200) + 50;
        int x = random.nextInt(width-500);
        int y = random.nextInt(height-900);

        DotView dotView = new DotView(this);
        dotView.setX(x);
        dotView.setY(y);
        dotView.setSize(size);
        dotView.setOnClickListener(this::onDotClick);

        ViewGroup container = findViewById(R.id.container);
        container.addView(dotView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        timer.cancel();
        Intent intent = new Intent(DotMainActivity.this, GameChooseScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.info_item){
            Toast.makeText(this, "This part of the app was made by Advait Shendage and you are using version: " + BuildConfig.VERSION_NAME, Toast.LENGTH_LONG).show();
        }

        return true;
    }


}