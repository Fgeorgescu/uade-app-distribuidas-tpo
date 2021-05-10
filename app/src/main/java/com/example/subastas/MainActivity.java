package com.example.subastas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.subastas.service.UserService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private UserService userService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userService = new UserService();

    }


    // Tiene que ser public y recibir view
    public void onLoginClick(View view) {
        EditText username = (EditText) findViewById(R.id.login_username_text);
        EditText password = (EditText) findViewById(R.id.login_password_text);


        userService.login(
                username.getText().toString(),
                password.getText().toString()
        );

        Log.i(TAG, "Credenciales: ");
    }


    public void onCreateUserClick(View view) {
        EditText username = (EditText) findViewById(R.id.createUser_username_text);
        EditText mail = (EditText) findViewById(R.id.createUser_mail_text);

        userService.createUser(
                username.getText().toString(), mail.getText().toString()
        );
    }

    public void onUpdatePasswordClick(View view) {
        EditText validationCode = (EditText) findViewById(R.id.updatePassword_code_text);
        EditText newPassword = (EditText) findViewById(R.id.updatePassword_password_text);
        EditText validationMail = (EditText) findViewById(R.id.updatePassword_username_text);

        userService.updatePassword(
                validationMail.getText().toString(),
                validationCode.getText().toString(),
                newPassword.getText().toString()
        );
    }

    public void ping(View view) {
        Log.e(TAG, "Executing ping");
        userService.ping();
    }


}