package com.android.moldovanbalazs.vallet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.moldovanbalazs.vallet.domain.User;
import com.android.moldovanbalazs.vallet.service.UserApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText usernameField, passwordField;
    Button login;

    private static final String BASE_URL = "http://192.168.1.244:8080/";
    private static Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField = findViewById(R.id.usernameEdit);
        passwordField = findViewById(R.id.passwordEdit);

        login = findViewById(R.id.loginButton);

        final UserApi api = getRetrofit().create(UserApi.class);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User(null, usernameField.getText().toString(), passwordField.getText().toString());
                Gson gson = new GsonBuilder().create();
                Call<User> userCall = api.getUser(gson.toJson(user));
                Log.d("Response", "just here");
                userCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            Log.d("Response", response.body().toString());
                            User returned = response.body();
                            SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("username", returned.getName());
                            editor.apply();
                            if(returned != null) {
                                Intent intent = new Intent(MainActivity.this, MainMenu.class);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(MainActivity.this,"Wrong credentials!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Something went wrong :(",
                                Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
