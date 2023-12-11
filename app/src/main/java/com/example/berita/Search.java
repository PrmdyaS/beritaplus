package com.example.berita;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Search extends AppCompatActivity {
    EditText search_bar;
    RecyclerView recyclerView, recyclerView2;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        search_bar = findViewById(R.id.search_bar);

        search_bar.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_clear_24, 0);

        search_bar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    int drawablePosition = (view.getWidth() - view.getPaddingEnd() - getResources().getDimensionPixelOffset(R.dimen.clearDrawableWidth));
                    if (motionEvent.getRawX() >= drawablePosition) {
                        search_bar.setText("");
                        return true;
                    }
                }
                return false;
            }
        });

        search_bar.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
        updateClearButtonVisibility(search_bar.getText().toString());

        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                updateClearButtonVisibility(editable.toString());
            }
        });

        search_bar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    databaseHelper = new DatabaseHelper(Search.this);
                    String searchQuery = search_bar.getText().toString().trim();
                    if (!searchQuery.isEmpty()) {
                        // Tambahkan pencarian ke database
                        databaseHelper.addSearchQuery(searchQuery);
                        // Lakukan tindakan pencarian
                        performSearch(searchQuery);
                    } else {
                        Toast.makeText(Search.this, "Masukkan kata kunci pencarian", Toast.LENGTH_SHORT).show();
                    }
                }
                return false; // Mengembalikan false jika event belum ditangani
            }
        });
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<String> searchHistoryList = databaseHelper.getAllSearchHistory();

        recyclerView = findViewById(R.id.recyclerView);
        HistoryAdapter historyAdapter = new HistoryAdapter(searchHistoryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(historyAdapter);

    }

    private void performSearch(String query) {
        recyclerView.setVisibility(View.GONE);
        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setVisibility(View.VISIBLE);
        String BASE_URL = "https://newsapi.org/v2/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsAPIService newsAPIService = retrofit.create(NewsAPIService.class);

        Call<NewsResponse> call = newsAPIService.getNewsByQuery(query);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    NewsResponse newsResponse = response.body();
                    if (newsResponse != null) {
                        List<Article> articles = newsResponse.getArticles();
                        NewsAdapter newsAdapter = new NewsAdapter(articles);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Search.this);
                        recyclerView2.setLayoutManager(layoutManager);
                        recyclerView2.setAdapter(newsAdapter);

                        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Article article, int position) {
                                String title = articles.get(position).getTitle();
                                String publishedAt = articles.get(position).getPublishedAt();
                                String urlToImage = articles.get(position).getUrlToImage();
                                String description = articles.get(position).getDescription();
                                String author = articles.get(position).getAuthor();
                                String content = articles.get(position).getContent();
                                String url = articles.get(position).getUrl();

                                Intent intent = new Intent(Search.this, PageBerita.class);
                                intent.putExtra("title", title);
                                intent.putExtra("publishedAt", publishedAt);
                                intent.putExtra("urlToImage", urlToImage);
                                intent.putExtra("description", description);
                                intent.putExtra("author", author);
                                intent.putExtra("content", content);
                                intent.putExtra("url", url);
                                startActivity(intent);
                            }
                        });
                    }
                } else {
                    // Handle jika responsenya tidak berhasil
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                // Handle jika permintaan gagal
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Pastikan untuk menutup koneksi database saat aktivitas dihancurkan
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }

    private void updateClearButtonVisibility(String text) {
        if (text.isEmpty()) {
            search_bar.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
        } else {
            search_bar.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_clear_24, 0);
        }
    }

    public void back(View view) {
        finish();
    }
}