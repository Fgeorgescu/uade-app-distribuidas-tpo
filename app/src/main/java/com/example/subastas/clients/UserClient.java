package com.example.subastas.clients;

import com.example.subastas.dto.users.http.request.UpdatePasswordRequest;
import com.example.subastas.dto.users.http.request.UserCreationRequest;
import com.example.subastas.dto.users.http.request.UserCredentials;
import com.example.subastas.dto.users.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserClient {
    String PING_URL = "/ping";
    String LOGIN_URL = "/login";
    String LOGOUT_URL = "/logout";
    String CREATE_USER_URL = "/users";
    String UPDATE_PASSWORD_URL = "/users/{user_id}/password";
    String UPDATE_USER_URL = "/users";


    @GET(PING_URL)
    Call<String> ping();

    @POST(CREATE_USER_URL)
    Call<UserInfo> createUser(@Body UserCreationRequest user);

    @PATCH(UPDATE_PASSWORD_URL)
    Call<UserInfo> updatePassword(@Path("user_id") String userId, @Body UpdatePasswordRequest user);

    /**
     * Creamos una nueva sesión para este device.
     * @param userCredentials
     * @return
     */
    @POST(LOGIN_URL)
    Call<UserInfo> login(@Body UserCredentials userCredentials);

    /**
     * Eliminamos la sesión
     * @return
     */
    @POST(LOGOUT_URL)
    Call<Object> logout();

}
