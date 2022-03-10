package com.example.quamtumtask.Models;

import com.example.quamtumtask.Models.NewsListRequest;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {

    String status;
    public String getStatus() {
        return status;
    }

    @SerializedName("articles")
    private List<NewsListRequest> newsListRequests = null;
    public List<NewsListRequest> getNewsListRequests() {
        return newsListRequests;
    }
}
