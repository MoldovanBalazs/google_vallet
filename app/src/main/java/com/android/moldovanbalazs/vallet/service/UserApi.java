package com.android.moldovanbalazs.vallet.service;

import com.android.moldovanbalazs.vallet.domain.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApi {

    @GET("validate/{json}")
    Call<User> getUser(@Path("json") String jsonToFetch);

}
