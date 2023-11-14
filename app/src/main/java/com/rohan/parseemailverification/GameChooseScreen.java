package com.rohan.parseemailverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.installreferrer.BuildConfig;

public class GameChooseScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_choose_screen);
    }

    public void openLionOrTigerGame(View view){
        Intent intentStartLionOrTiger = new Intent(GameChooseScreen.this, GameActivity.class);
//        intentStartLionOrTiger.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentStartLionOrTiger);
    }

    public void openSwipeGame(View view){
        Intent intentStartSwipe = new Intent(GameChooseScreen.this, SwipeMainActivity.class);
//        intentStartLionOrTiger.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentStartSwipe);
    }

    public void openDotGame(View view){
        Intent intentStartDot = new Intent(GameChooseScreen.this, DotMainActivity.class);
//        intentStartLionOrTiger.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentStartDot);
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.info_item){
            Toast.makeText(this, "This app was made by Rohan Udhwani and you are using version: " + BuildConfig.VERSION_NAME, Toast.LENGTH_LONG).show();
        }

        return true;
    }
}