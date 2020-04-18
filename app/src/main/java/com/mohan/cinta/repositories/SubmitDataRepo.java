package com.mohan.cinta.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.mohan.cinta.network.ApiClient;
import com.mohan.cinta.network.ResponseData;
import com.mohan.cinta.network.RetrofitFactory;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitDataRepo {

    private static SubmitDataRepo instance;
    private ApiClient apiClient;

    private SubmitDataRepo() {
        apiClient = RetrofitFactory.createService(ApiClient.class);
    }

    public static SubmitDataRepo getInstance() {
        if (instance == null) {
            instance = new SubmitDataRepo();
        }
        return instance;
    }

    @NonNull
    public MutableLiveData<ResponseData> submitDataCall(JsonObject jsonObject) {
        MutableLiveData<ResponseData> responseDataMutableLiveData = new MutableLiveData<>();

        apiClient.sendData(jsonObject).enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(@NonNull Call<ResponseData> call, @NonNull Response<ResponseData> response) {

                if (response.isSuccessful()) {
                    responseDataMutableLiveData.setValue(response.body());
                }

                Log.e("SUCCESS", response.toString());
            }

            @Override
            public void onFailure(@NonNull Call<ResponseData> call, @NonNull Throwable t) {
                ResponseData responseData = new ResponseData();
                responseData.setErrormessage(Objects.requireNonNull(t.getMessage()));
                responseDataMutableLiveData.setValue(responseData);
                Log.e("ERROR", Objects.requireNonNull(t.getMessage()));
            }
        });
        return responseDataMutableLiveData;
    }
}
