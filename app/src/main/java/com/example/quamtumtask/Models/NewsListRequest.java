package com.example.quamtumtask.Models;

import com.google.gson.annotations.SerializedName;

public class NewsListRequest {

    String author;
    String title;
    String description;
    String urlToImage;
    String publishedAt;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }


    @SerializedName("source")
    NewsNameResquest newsNameResquest;
    public NewsNameResquest getNewsNameResquest() {
        return newsNameResquest;
    }







}
