package com.example.berita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class test extends AppCompatActivity {
    TextView test;
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        test = findViewById(R.id.textsa);
        test.setText(BASE_URL);
        // Inisialisasi Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Panggil API menggunakan Retrofit
        getTopHeadlines();
    }

    private void getTopHeadlines() {
        NewsAPIService newsAPIService = retrofit.create(NewsAPIService.class);
        Call<NewsResponse> call = newsAPIService.getTopHeadlines("us", "technology", 1);

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    NewsResponse newsResponse = response.body();
                    if (newsResponse != null) {
                        List<Article> articles = newsResponse.getArticles();
                        String title = articles.get(1).getTitle();
                        test.setText(title);
                    }
                } else {
                    // Handle kesalahan jika responsenya tidak berhasil
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                // Handle kesalahan jika permintaan gagal
            }
        });
    }
}