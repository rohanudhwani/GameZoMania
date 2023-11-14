package com.rohan.parseemailverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail, edtUsername, edtPassword;
    Button btnLoginPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseInstallation.getCurrentInstallation().saveInBackground();

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtLoginUsername);

        btnLoginPage = findViewById(R.id.btnLoginPage);

    }

    public void signupIsPressed(View btnView){

        try{
            ParseUser user = new ParseUser();
            user.setUsername(edtUsername.getText().toString());
            user.setPassword(edtPassword.getText().toString());
            user.setEmail(edtEmail.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e==null) {
                        ParseUser.logOut();
                        alertDisplayer("Account Created Successfully!", "Please verify your email before login", false);
                        btnLoginPage.setVisibility(View.VISIBLE);

                    }
                    else {
                        ParseUser.logOut();
                        alertDisplayer("Error Account Creation failed", "Account could not be created" + e.getMessage(), true);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void alertDisplayer(String title, String message, boolean error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.cancel();
                    // don't forget to change the line below with the names of your Activities
                    if (!error) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

    public void loginPageIsPressed(View buttonView){
        btnLoginPage.setVisibility(View.INVISIBLE);
        Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intentLogin);

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