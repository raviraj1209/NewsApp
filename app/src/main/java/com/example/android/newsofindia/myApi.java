package com.example.android.newsofindia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface myApi {
    @GET(" https://newsapi.org/v2/top-headlines?country=us&apiKey=5e3d75d93256402cb07f1ba676658c01")
    Call<List<NewsModel>> getmodels();
}
