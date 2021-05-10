package com.example.subastas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.subastas.clients.UserClient;
import com.example.subastas.dto.users.UserCreationRequest;
import com.example.subastas.dto.users.UserCredentials;
import com.example.subastas.dto.users.UserInfo;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private UserClient userClient = new Retrofit.Builder()
                                    // 10.0.2.2 hace referencia a la computadora donde se ejecuta el emulador, 127.0.0.1 hace referencia al emulador en si
                                    .baseUrl("http://10.0.2.2:8080")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build().create(UserClient.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    // Tiene que ser public y recibir view
    public void onLoginClick(View view) {
        EditText username = (EditText) findViewById(R.id.login_username_text);
        EditText password = (EditText) findViewById(R.id.login_password_text);


        /*
        Call<String> call = client.ping();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    Log.i(TAG, "Response!!! " + response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "No ping; :( ", t);
            }
        });*/

        UserCredentials creads = new UserCredentials(username.getText().toString(),password.getText().toString());


        Log.i(TAG, "Credenciales: " +creads.toString());
    }


    public void onCreateUserClick(View view) {
        EditText username = (EditText) findViewById(R.id.createUser_username_text);
        EditText mail = (EditText) findViewById(R.id.createUser_mail_text);

        Call<UserInfo> call = userClient.createUser(new UserCreationRequest(username.getText().toString(), mail.getText().toString()));

        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                Log.i(TAG, "Usuario creado!!! " + response.body().toString());
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.e(TAG, "No ping; :( ", t);
            }
        });
    }
}