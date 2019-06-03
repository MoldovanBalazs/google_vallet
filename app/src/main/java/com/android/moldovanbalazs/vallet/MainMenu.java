package com.android.moldovanbalazs.vallet;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.moldovanbalazs.vallet.domain.Wallet;
import com.android.moldovanbalazs.vallet.service.WalletApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainMenu extends AppCompatActivity {

    TextView textView, greeting;
    RecyclerView recyclerView;

    private static final String BASE_URL = "http://192.168.1.244:8080/";
    private static Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        greeting = findViewById(R.id.greeting);
        SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        greeting.setText("Welcome " + prefs.getString("username", null) + "!");
        Log.d("Username", prefs.getString("username", null));

        WalletApi api = getRetrofit().create(WalletApi.class);
        Call<List<Wallet>> persons = api.getUserWallets("balazs");
        persons.enqueue(new Callback<List<Wallet>>() {
            @Override
            public void onResponse(Call<List<Wallet>> call, Response<List<Wallet>> response) {
                if (response.isSuccessful()) {
                    Log.d("Response", response.body().toString());
                    List<Wallet> wallets = response.body();
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(wallets);
                    recyclerView.setAdapter(adapter);
                    //textView.setText(response.body().toString());
                } else {

                    Log.d("Response", "Response code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Wallet>> call, Throwable t) {
                Log.w("Response", "Error in call", t);
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
