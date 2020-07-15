package com.saugat.bagpacker.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import com.saugat.bagpacker.Activity.user.Login;
import com.saugat.bagpacker.R;
import com.saugat.bagpacker.api.UsersAPI;
import com.saugat.bagpacker.model.User;
import com.saugat.bagpacker.serverresponse.ImageResponse;
import com.saugat.bagpacker.strictmode.StrictModeClass;
import com.saugat.bagpacker.url.Url;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.saugat.bagpacker.url.Url.BASE_URL;
import static com.saugat.bagpacker.url.Url.imagePath;
import static com.saugat.bagpacker.url.Url.token;

public class profileFragment extends Fragment{

    ImageView proPic;
    TextView tvfname, tvuname;
    EditText etphn, eteml;
    Button btnedit, btnlogout;
    String imageName;
    String old;
    boolean click = false;
    String imagePath;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.profile, container, false);

        proPic = view.findViewById(R.id.imgProfileImg);
        tvfname=view.findViewById(R.id.tvfname);
        tvuname=view.findViewById(R.id.tvuname);
        etphn=view.findViewById(R.id.etphn);
        eteml=view.findViewById(R.id.eteml);
        btnedit=view.findViewById(R.id.btnProfUpdate);
        btnlogout=view.findViewById(R.id.btnLogout);
        loadCurrentUser();
        proPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
                click = true;
//                saveImageOnly();
            }
        });


        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               logout();
            }

        });

         btnedit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 edit();
             }
         });

        return view;
    }



    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(getContext(), "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        proPic.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getContext(),
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
            Toast.makeText(getContext(), "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getContext(), "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void edit(){
        saveImageOnly();
        if (!click){
            imageName=old;
        };
        User users = new User(
                etphn.getText().toString(),
                eteml.getText().toString(),
                imageName

        );
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<User> updateCall = usersAPI.updateUser(token,users);

        updateCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getActivity(), "succesfully updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCurrentUser() {

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<User> userCall = usersAPI.getUserDetails(token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String imgPath = Url.imagePath +  response.body().getImage();


                Picasso.get().load(imgPath).into(proPic);
                imageName=response.body().getImage();
                old=response.body().getImage();
                tvfname.setText(response.body().getFullname());
                tvuname.setText(response.body().getUsername());
                etphn.setText(response.body().getPhone());
                eteml.setText(response.body().getEmail());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(getActivity(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setMessage("Do you want to Logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("token");
                editor.remove("username");
                editor.remove("id");
                editor.commit();
                Url.token = "Bearer ";
                Url.id="";
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
