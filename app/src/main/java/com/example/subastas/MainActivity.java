package com.example.subastas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.subastas.clients.UserClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Retrofit userClient = new Retrofit.Builder()
                                    // 10.0.2.2 hace referencia a la computadora donde se ejecuta el emulador, 127.0.0.1 hace referencia al emulador en si
                                    .baseUrl("http://10.0.2.2:8080")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // Tiene que ser public y recibir view
    public void onLoginClick(View view) {
        EditText username = (EditText) findViewById(R.id.login_username_text);
        EditText password = (EditText) findViewById(R.id.login_password_text);

        UserClient client = userClient.create(UserClient.class);

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
        });


        Log.i(TAG, "Credenciales: " +username.getText()+password.getText());
    }
}