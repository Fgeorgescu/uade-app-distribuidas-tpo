package com.example.subastas.service;

import android.util.Log;

import com.example.subastas.clients.UserClient;
import com.example.subastas.dto.users.http.request.UpdatePasswordRequest;
import com.example.subastas.dto.users.http.request.UserCreationRequest;
import com.example.subastas.dto.users.UserInfo;
import com.example.subastas.dto.users.http.request.UserCredentials;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserService {
    private static final String TAG = "UserService";

    UserClient userClient;
    OkHttpClient okHttpClient;
    CookieHandler cookieHandler;

    public UserService() {
        // Necesario para soportar sesiones
        cookieHandler = new CookieManager();
        // Necesario para soportar sesiones
        okHttpClient = new OkHttpClient()
                .newBuilder()
                .cookieJar(new JavaNetCookieJar(cookieHandler))
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        this.userClient = new Retrofit.Builder()
                // 10.0.2.2 hace referencia a la computadora donde se ejecuta el emulador, 127.0.0.1 hace referencia al emulador en si
                .baseUrl("http://10.0.2.2:8080")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UserClient.class);


    }

    public void createUser(String username, String mail) {
        Call<UserInfo> call = userClient.createUser(new UserCreationRequest(username, mail));

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

    public void updatePassword(String username, String validationCode, String newPassword) {

        Call<UserInfo> call = userClient.updatePassword(
                "1",
                new UpdatePasswordRequest(username, validationCode, newPassword));

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

    public void login(String username, String password) {
        Call<UserInfo> call = userClient.login(new UserCredentials(username, password));

        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.code() < 300) {
                    Log.i(TAG, "PONG!!! " + response.body());
                } else {
                    Log.i(TAG, "Pong raro... " + response.errorBody().toString());

                }            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.e(TAG, "No ping; :( ", t);
            }
        });
    }

    public void ping() {
        Call<String> call = userClient.ping();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() < 300) {
                    Log.i(TAG, "PONG!!! " + response.body().getBytes().toString());
                } else {
                    Log.i(TAG, "Pong raro... " + response.errorBody().toString());

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "No ping; :( ", t);
            }
        });
    }
}
