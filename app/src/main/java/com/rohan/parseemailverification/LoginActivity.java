package com.rohan.parseemailverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText edtLoginUsername, edtLoginPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLoginUsername = findViewById(R.id.edtLoginUsername);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
    }

    public void loginIsPressed(View btnView){

        ParseUser.logInInBackground(edtLoginUsername.getText().toString(), edtLoginPassword.getText().toString(), (parseUser, e) -> {
            if (parseUser != null) {
                if (parseUser.getBoolean("emailVerified")){
                    alertDisplayer("Login Successful", "Welcome, " + edtLoginUsername.getText().toString() + "!", false);

                }
                else{
                    ParseUser.logOut();
                    alertDisplayer("Login Fail",  " Please verify your Email first", true);
                }

            } else {
                ParseUser.logOut();
                alertDisplayer("Login Fail", e.getMessage() + " Please try again", true);
            }
        });

    }

    private void alertDisplayer(String title, String message, boolean error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.cancel();
                    // don't forget to change the line below with the names of your Activities
                    if (!error) {
                        Intent intent = new Intent(LoginActivity.this, GameChooseScreen.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

    public void signUpPageIsPressed(View buttonView){
        Intent intentSignUp = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intentSignUp);

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