package com.example.quamtumtask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quamtumtask.Models.NewsListRequest;
import com.example.quamtumtask.Models.NewsResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String TAG = "HomeActivity";
    NewsAdapter newsAdapter;
    List<NewsListRequest> newsListRequests = new ArrayList<NewsListRequest>();
    RecyclerView recyclerView;
    EditText txt_searchTxt;
    TextView tv_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.recyclerView);
        txt_searchTxt = findViewById(R.id.txt_searchTxt);
        tv_logout = findViewById(R.id.tv_logout);
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        txt_searchTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        myratingApi();
    }
    private void filter(String text) {
        List<NewsListRequest> newsListRequests1 = new ArrayList<NewsListRequest>();
        for (NewsListRequest item : newsListRequests){
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())){
                newsListRequests1.add(item);
            }
        }
        newsAdapter.filteredListed(newsListRequests1);
    }
    private void myratingApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        RetroftInterface service = retrofit.create(RetroftInterface.class);
        Call<NewsResponse> call = service.getNews("google-news-in", "a6f27e93a39848059ca2707ca82cf953");
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                Log.d(TAG, "onResponse: "+new Gson().toJson(response.body()));
                Log.d(TAG, "onResponse: "+response.message());
                Log.d(TAG, "onResponse: "+response.code());
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("ok")) {
                        newsListRequests = response.body().getNewsListRequests();
                        newsAdapter = new NewsAdapter(HomeActivity.this, newsListRequests);
                        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.VERTICAL,false));
                        recyclerView.setAdapter(newsAdapter);
                        newsAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                t.printStackTrace();
               Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

 private void logout(){
     FirebaseAuth.getInstance().signOut();
     Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show();
     Intent intent = new Intent(HomeActivity.this, MainActivity.class);
     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//makesure user cant go back
     startActivity(intent);

 }
}