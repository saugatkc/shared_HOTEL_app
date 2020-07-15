package com.saugat.bagpacker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.saugat.bagpacker.Activity.user.Login;
import com.saugat.bagpacker.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent openHome = new Intent(SplashScreenActivity.this, Login.class);
                startActivity(openHome);
                finish();
            }
        }, 2000);
    }

}
