package com.example.subastas.clients;

import com.example.subastas.dto.users.UserCredentials;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserClient {
    String PING_URL = "/ping";
    String LOGIN_URL = "/login";
    String LOGOUT_URL = "/logout";
    String CREATE_USER_URL = "/users";
    String UPDATE_USER_URL = "/users";


    @GET(PING_URL)
    Call<String> ping();

    @POST(CREATE_USER_URL)
    Call<Object> createUser(Object user);

    /**
     * Creamos una nueva sesión para este device.
     * @param userCredentials
     * @return
     */
    @POST(LOGIN_URL)
    Call<Object> login(@Body UserCredentials userCredentials);

    /**
     * Eliminamos la sesión
     * @return
     */
    @POST(LOGOUT_URL)
    Call<Object> logout();

}
