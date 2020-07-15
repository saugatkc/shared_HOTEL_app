package com.saugat.bagpacker.Activity.user;


import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.NotificationCompat;

import com.saugat.bagpacker.Boardcast.BroadCastReceiver;
import com.saugat.bagpacker.R;
import com.saugat.bagpacker.api.UsersAPI;
import com.saugat.bagpacker.createchannel.CreateChannel;
import com.saugat.bagpacker.model.User;
import com.saugat.bagpacker.strictmode.StrictModeClass;
import com.saugat.bagpacker.bll.LoginBLL;
import com.saugat.bagpacker.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.saugat.bagpacker.url.Url.token;

public class Login extends AppCompatActivity {

    private Button btnLogin;
    private EditText etUsername, etPassword;
    private TextView tvSignup;
    String username,id;
    public NotificationManagerCompat notificationManagerCompat;
    Vibrator vibrator;
    SharedPreferences rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        tvSignup = findViewById(R.id.tvSignup);
        btnLogin = findViewById(R.id.btnLogin);
        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);

        rememberMe = getSharedPreferences("User", Context.MODE_PRIVATE);
        String token = rememberMe.getString("token","empty");
        if(!token.equals("empty")){
            Url.token = token;
            username = rememberMe.getString("username","");
            Url.id = rememberMe.getString("id","");
            notifiy();
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }



        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(); }
        });
    }

    private void notifiy() {
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_local_hotel_black_24dp)
                .setContentTitle("Bagpacker")
                .setContentText("Login success :" + username)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    private void login() {
        username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        LoginBLL loginBLL = new LoginBLL();

        StrictModeClass.StrictMode();
        if (loginBLL.checkUser(username, password)) {
            SharedPreferences.Editor editor = rememberMe.edit();

            editor.putString("username", username);
            editor.putString("token", Url.token);
            editor.commit();
            notifiy();
            saveid();
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);


            finish();
        } else {
            vibrator.vibrate(2000);
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
            etUsername.requestFocus();
        }
    }

    private void saveid() {
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<User> userCall = usersAPI.getUserDetails(Url.token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Login.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Url.id=response.body().get_id();
                SharedPreferences.Editor editor = rememberMe.edit();
                editor.putString("id", Url.id);
                editor.commit();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(Login.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    BroadCastReceiver broadCastReceiver= new BroadCastReceiver(this);

    protected void onStart(){
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadCastReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadCastReceiver);
    }
}