package com.melihkarakilinc.sondepremler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DepremApi {

    @GET("api/pure_api.php")
    Call<List<DepremInf>> GetDeprem();
}
