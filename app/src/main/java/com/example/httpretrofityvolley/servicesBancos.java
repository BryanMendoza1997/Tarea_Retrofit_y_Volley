package com.example.httpretrofityvolley;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface servicesBancos {

    @GET("bankList")
    Call<List<Datos>> getusersGet(@Header("Public-Merchant-Id") String id);

}
