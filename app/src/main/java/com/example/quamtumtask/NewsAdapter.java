package com.example.quamtumtask;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quamtumtask.Models.NewsListRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    Context context;
    List<NewsListRequest> newsListRequests = new ArrayList<>();
    public NewsAdapter(Context context,List<NewsListRequest> newsListRequests) {
        this.context = context;
        this.newsListRequests = newsListRequests;
    }
    @NonNull
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_adapter, parent, false);
        return new NewsAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.MyViewHolder holder, final int position) {
        String output = newsListRequests.get(position).getTitle().substring(0, 1).toUpperCase() + newsListRequests.get(position).getTitle().substring(1);
        holder.tv_title.setText(output);
        holder.tv_new_pub.setText(newsListRequests.get(position).getPublishedAt());
        holder.tv_published.setText(newsListRequests.get(position).getNewsNameResquest().getName());
        holder.tv_new_des.setText(newsListRequests.get(position).getDescription());
        Picasso.get().load(newsListRequests.get(position).getUrlToImage()).into(holder.imgage);
    }
    public void filteredListed(List<NewsListRequest> newsListRequests1) {
        newsListRequests = newsListRequests1;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return newsListRequests.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_new_des, tv_published, tv_new_pub;
        ImageView imgage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_new_des = itemView.findViewById(R.id.tv_new_des);
            tv_published = itemView.findViewById(R.id.tv_published);
            imgage = itemView.findViewById(R.id.imgage);
            tv_new_pub = itemView.findViewById(R.id.tv_new_pub);
        }
    }
  }