package com.mohan.cinta.network;

import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    @NonNull
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().connectTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES);

    @NonNull
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl("http://instapreps.com/api/")
                    .addConverterFactory(
                            GsonConverterFactory.create());

    @NonNull
    private static Retrofit retrofit =
            builder.client(httpClient.build())
                    .build();

    @NonNull
    public static <S> S createService(@NonNull Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
