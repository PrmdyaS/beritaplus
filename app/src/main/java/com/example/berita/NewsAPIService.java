package com.example.berita;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPIService {
    // Membuat bagian tetap dari URL sebagai basis
    @GET("top-headlines?apiKey=cbb4cce0e1e845afbbc866352c49f454&pageSize=10")
    Call<NewsResponse> getTopHeadlines(
            @Query("country") String country,
            @Query("category") String category,
            @Query("page") int page
    );

    @GET("everything?apiKey=cbb4cce0e1e845afbbc866352c49f454&pageSize=10")
    Call<NewsResponse> getNewsByQuery(
            @Query("q") String q
    );
}