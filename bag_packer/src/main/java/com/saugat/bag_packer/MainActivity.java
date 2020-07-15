package com.saugat.bag_packer;

import android.app.Notification;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.saugat.bag_packer.bll.LoginBLL;
import com.saugat.bag_packer.strictmode.StrictModeClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends WearableActivity {

//    private TextView mTextView;
    private Button btnLogin;
    private EditText etUsername, etPassword;
//    private TextView tvSignup;
//    String username,id;
//    public NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTextView = (TextView) findViewById(R.id.text);

        // Enables Always-on
        setAmbientEnabled();

//            notificationManagerCompat = NotificationManagerCompat.from(this);
//            CreateChannel channel = new CreateChannel(this);
//            channel.createChannel();
            etUsername = findViewById(R.id.etUsername);
            etPassword = findViewById(R.id.etPassword);
            btnLogin = findViewById(R.id.btnLogin);

//            SharedPreferences sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
//            String token = sharedPreferences.getString("token","empty");
//            if(!token.equals("empty")){
//                Url.token = token;
//                username = sharedPreferences.getString("username","empty");
//                Url.id = sharedPreferences.getString("id","empty");
//                notifiy();
//                Intent intent = new Intent(Login.this, MainActivity.class);
//                startActivity(intent);
//            }


            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login(); }
            });
        }

//        private void notifiy() {
//            Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1)
//                    .setSmallIcon(R.drawable.ic_local_hotel_black_24dp)
//                    .setContentTitle("Bagpacker")
//                    .setContentText("Login success :" + username)
//                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
//                    .build();
//
//            notificationManagerCompat.notify(1, notification);
//        }

        private void login() {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            LoginBLL loginBLL = new LoginBLL();

            StrictModeClass.StrictMode();
            if (loginBLL.checkUser(username, password)) {
//                SharedPreferences sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("token", Url.token);
//                editor.putString("username", username);
//                editor.putString("id", id);
//                editor.commit();
//
//                saveid();
//                Url.id=id;
//                notifiy();
                Intent intent = new Intent(MainActivity.this, dashboard.class);
                startActivity(intent);


                finish();
            } else {
                Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
                etUsername.requestFocus();
            }
        }

//        private void saveid() {
//            UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
//            Call<User> userCall = usersAPI.getUserDetails(token);
//
//            userCall.enqueue(new Callback<User>() {
//                @Override
//                public void onResponse(Call<User> call, Response<User> response) {
//                    if (!response.isSuccessful()) {
//                        Toast.makeText(Login.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    id=response.body().get_id();
//                }
//
//                @Override
//                public void onFailure(Call<User> call, Throwable t) {
//
//                    Toast.makeText(Login.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }

//        BroadCastReceiver broadCastReceiver= new BroadCastReceiver(this);
//
//        protected void onStart(){
//            super.onStart();
//            IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//            registerReceiver(broadCastReceiver,intentFilter);
//        }
//
//        @Override
//        protected void onStop() {
//            super.onStop();
//            unregisterReceiver(broadCastReceiver);
//        }

}
