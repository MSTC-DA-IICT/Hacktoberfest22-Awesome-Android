package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.CategoryClickInterface {

    private RecyclerView news,category;
    private ProgressBar progressBar;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<Categories_Modal> categoriesModalArrayList;
    private CategoryAdapter categoryAdapter;
    private NewsAdapter newsAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        // API KEY 449c333f89004a4498d38bb0d524c63b

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        news=findViewById(R.id.news);
        category=findViewById(R.id.categories);
        progressBar=findViewById(R.id.progress_bar);
        articlesArrayList=new ArrayList<>();
        categoriesModalArrayList=new ArrayList<>();
        newsAdapter=new NewsAdapter(articlesArrayList,this);
        categoryAdapter=new CategoryAdapter(categoriesModalArrayList,this,this::onCategoryClick);
        news.setLayoutManager(new LinearLayoutManager(this));
        news.setAdapter(newsAdapter);
        category.setAdapter(categoryAdapter);
        getCategories();
        getNews("All");
        newsAdapter.notifyDataSetChanged();
    }

    private void getCategories(){
        categoriesModalArrayList.add(new Categories_Modal("All","https://images.unsplash.com/photo-1623039405147-547794f92e9e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fG5ld3NwYXBlcnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"));
        categoriesModalArrayList.add(new Categories_Modal("General","https://images.unsplash.com/photo-1566378246598-5b11a0d486cc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8bmV3c3BhcGVyfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        categoriesModalArrayList.add(new Categories_Modal("Entertainment","https://images.unsplash.com/photo-1586899028174-e7098604235b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8ZW50ZXJ0YWlubWVudHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"));
        categoriesModalArrayList.add(new Categories_Modal("Sports","https://images.unsplash.com/photo-1517649763962-0c623066013b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8c3BvcnRzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        categoriesModalArrayList.add(new Categories_Modal("Business","https://images.unsplash.com/photo-1579532537598-459ecdaf39cc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjJ8fGJ1c2luZXNzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        categoriesModalArrayList.add(new Categories_Modal("Health","https://images.unsplash.com/photo-1506126613408-eca07ce68773?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8aGVhbHRofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        categoriesModalArrayList.add(new Categories_Modal("Technology","https://images.unsplash.com/photo-1550751827-4bd374c3f58b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60"));
        categoriesModalArrayList.add(new Categories_Modal("Science","https://images.unsplash.com/photo-1554475900-0a0350e3fc7b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8c2NpZW5jZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"));
        categoryAdapter.notifyDataSetChanged();
    }
    private void getNews(String category){
        progressBar.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryURL="https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apikey=449c333f89004a4498d38bb0d524c63b";
        String url="https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=449c333f89004a4498d38bb0d524c63b";
        String BASE_URL="https://newsapi.org/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI;
        retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;
        if(category.equals("All")){
            call=retrofitAPI.getAllNews(url);
        }
        else{
            call=retrofitAPI.getNewsByCategory(categoryURL);
        }
        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal= response.body();
                progressBar.setVisibility(View.GONE);
                ArrayList<Articles> articles=newsModal.getArticles();
                for(int i=0;i<articles.size();i++){
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));
                }
                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail to get news", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onCategoryClick(int position) {
        String category=categoriesModalArrayList.get(position).getCategory();
        getNews(category);

    }
}