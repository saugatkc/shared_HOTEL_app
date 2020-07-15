package com.saugat.bagpacker.Activity.user;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.saugat.bagpacker.Activity.ShakeDetector;
import com.saugat.bagpacker.R;
import com.saugat.bagpacker.strictmode.StrictModeClass;
import com.saugat.bagpacker.api.UsersAPI;
import com.saugat.bagpacker.model.User;
import com.saugat.bagpacker.serverresponse.ImageResponse;
import com.saugat.bagpacker.serverresponse.SignUpResponse;
import com.saugat.bagpacker.url.Url;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private CircleImageView imgProfile;
    private EditText etFullName, etPhone,etEmail,etSignUpUsername, etSignUpPassword, etConfirmPassword;
    private Button btnSignup;
    String imagePath;
    private String imageName = "";
    private ShakeDetector mShakeDetector;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        imgProfile = findViewById(R.id.imgProfile);
        etFullName = findViewById(R.id.etFullName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etSignUpUsername = findViewById(R.id.etSignUpUsername);
        etSignUpPassword = findViewById(R.id.etSignUpPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSignup = findViewById(R.id.btnSignup);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                etFullName.setText("");
                etPhone.setText("");
                etEmail.setText("");
                etSignUpUsername.setText("");
                etSignUpPassword.setText("");
                etConfirmPassword.setText("");
                Picasso.get().load(R.drawable.noimage).into(imgProfile);

            }
        });
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSignUpPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                    if(validate()) {
                        saveImageOnly();
                        signUp();
                    }
                } else {
                    Toast.makeText(Register.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    etSignUpPassword.requestFocus();
                    return;
                }

            }
        });
    }

    private boolean validate() {
        boolean status=true;
        if (etSignUpUsername.getText().toString().length() < 6) {
            etSignUpUsername.setError("Minimum 6 character");
            status=false;
        }
        return status;
    }

    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imgProfile.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(), requestBody);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<ImageResponse> responseBodyCall = usersAPI.uploadImage(body);

        StrictModeClass.StrictMode();
        //Synchronous methid
        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void signUp() {



        String fullname = etFullName.getText().toString();
        String phone = etPhone.getText().toString();
        String email = etEmail.getText().toString();
        String username = etSignUpUsername.getText().toString();
        String password = etSignUpPassword.getText().toString();

        User users = new User(fullname, username, password, phone, email, imageName);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<SignUpResponse> signUpCall = usersAPI.registerUser(users);

        signUpCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Register.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(Register.this, "Registered", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(Register.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }


}
