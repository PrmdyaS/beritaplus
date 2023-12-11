package com.example.berita;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BeritaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeritaFragment extends Fragment {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private Retrofit retrofit;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int pagesize;
    private boolean isLoading = false;
    String country = "us";
    String category = "technology";
    int pageNumber = 1;
    List<Article> articleList = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BeritaFragment() {
        // Required empty public constructor
    }

    public static BeritaFragment newInstance() {
        BeritaFragment fragment = new BeritaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_berita, container, false);
        TextView title0 = view.findViewById(R.id.title0);
        TextView desc0 = view.findViewById(R.id.desc0);
        ImageView thumbnail0 = view.findViewById(R.id.thumbnail0);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsAPIService newsAPIService = retrofit.create(NewsAPIService.class);
        Call<NewsResponse> call = newsAPIService.getTopHeadlines(country, category, pageNumber);

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                isLoading = true;
                if (response.isSuccessful()) {
                    NewsResponse newsResponse = response.body();
                    if (newsResponse != null) {
                        List<Article> articles = newsResponse.getArticles();
                        String title = articles.get(0).getTitle();
                        String content = articles.get(0).getContent();
                        String urlToImage = articles.get(0).getUrlToImage();
                        LinearLayout header0 = view.findViewById(R.id.header0);
                        header0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String title = articles.get(0).getTitle();
                                String publishedAt = articles.get(0).getPublishedAt();
                                String urlToImage = articles.get(0).getUrlToImage();
                                String description = articles.get(0).getDescription();
                                String author = articles.get(0).getAuthor();
                                String content = articles.get(0).getContent();
                                String url = articles.get(0).getUrl();

                                Intent intent = new Intent(getActivity(), PageBerita.class);
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
                        if (urlToImage != null) {
                            Picasso.get().load(urlToImage).into(thumbnail0);
                        }
                        if (title != null) {
                            if (title.length() > 80) {
                                title = title.substring(0, 80) + "...";
                            }
                            title0.setText(title);
                        }
                        if (content != null) {
                            if (content.length() > 88) {
                                content = content.substring(0, 88) + "...";
                            }
                            desc0.setText(content);
                        }


                        List<Article> newArticles = articles.subList(1, articles.size());
                        articleList.addAll(newArticles);
                        NewsAdapter newsAdapter = new NewsAdapter(articleList);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(newsAdapter);
                        pagesize = articleList.size();
                        isLoading = false;

                        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Article article, int position) {
                                String title = articleList.get(position).getTitle();
                                String publishedAt = articleList.get(position).getPublishedAt();
                                String urlToImage = articleList.get(position).getUrlToImage();
                                String description = articleList.get(position).getDescription();
                                String author = articleList.get(position).getAuthor();
                                String content = articleList.get(position).getContent();
                                String url = articleList.get(position).getUrl();

                                Intent intent = new Intent(getActivity(), PageBerita.class);
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
                    // Handle kesalahan jika responsenya tidak berhasil
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                // Handle kesalahan jika permintaan gagal
            }
        });

        if (pagesize<20) {
            if (!isLoading) {
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                        int visibleItemCount = layoutManager.getChildCount();
                        int totalItemCount = layoutManager.getItemCount();
                        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                                && firstVisibleItemPosition >= 0
                                && totalItemCount >= pagesize) {
                            int nextPage = pageNumber + 1;
                            Call<NewsResponse> call1 = newsAPIService.getTopHeadlines(country, category, nextPage);
                            call1.enqueue(new Callback<NewsResponse>() {
                                @Override
                                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                                    NewsResponse newsResponse = response.body();
                                    List<Article> newArticles = newsResponse.getArticles();
                                    articleList.addAll(newArticles); // Tambahkan semua artikel baru ke articleList
                                    // Perbarui nomor halaman dan ukuran halaman
                                    pageNumber = nextPage;
                                    pagesize = articleList.size();

                                    // Perbarui adapter RecyclerView dengan data yang sudah diperbarui
                                    recyclerView.getAdapter().notifyDataSetChanged();
                                    isLoading = false;
                                }

                                @Override
                                public void onFailure(Call<NewsResponse> call, Throwable t) {
                                }
                            });
                        }
                    }
                });
            }
        }
        return view;
    }

    public interface OnFragmentListener {
    }
}