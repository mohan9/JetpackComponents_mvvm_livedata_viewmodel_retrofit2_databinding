package com.mohan.cinta.network;


import androidx.annotation.NonNull;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface ApiClient {
    @NonNull
    @Headers({
            "Content-Type: application/json",
    })
    @POST("enrollnow")
    Call<ResponseData> sendData(@Body JsonObject jsonObject);
}
