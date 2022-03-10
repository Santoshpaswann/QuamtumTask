package com.example.quamtumtask;

import com.example.quamtumtask.Models.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroftInterface {
    @GET("top-headlines")
    Call<NewsResponse> getNews(@Query("sources") String sources, @Query("apiKey") String apiKey );
}
