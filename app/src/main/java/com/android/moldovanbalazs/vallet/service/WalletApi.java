package com.android.moldovanbalazs.vallet.service;

import com.android.moldovanbalazs.vallet.domain.User;
import com.android.moldovanbalazs.vallet.domain.Wallet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WalletApi {

    @GET("wallets/{username}")
    Call<List<Wallet>> getUserWallets(@Path("username") String jsonToFetch);
}
