package com.example.berita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PageBerita extends AppCompatActivity {
    private TextView txtTitle, txtPublishedAt, txtDescription, txtAuthor, txtNewsContent, txtUrl;
    private ImageView bookmark, ivUrlToImage;
    private Boolean isBookmarked = false;
    String title, publishedAt, urlToImage, description, author, content, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_berita);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Intent intent = getIntent();
        if (intent != null) {
            title = intent.getStringExtra("title");
            publishedAt = intent.getStringExtra("publishedAt");
            urlToImage = intent.getStringExtra("urlToImage");
            description = intent.getStringExtra("description");
            author = intent.getStringExtra("author");
            content = intent.getStringExtra("content");
            url = intent.getStringExtra("url");

            // Gunakan data yang telah Anda terima di sini
        }
//        String htmlText = "<p>Justin Hubner punya target luar biasa bersama Timnas Indonesia. Pemain berdarah Belanda itu bertekad bawa Garuda mentas di Piala Dunia.</p>" +
//                "<p>Hubner resmi menjadi WNI usai setelah menjalani sumpah setia di Kanwil Kemenkumham DKI Jakarta, Rabu (6/12).</p>" +
//                "<p>Dengan demikian Hubner berpeluang membela Timnas Indonesia di berbagai ajang. Kapten Wolves U-21 itu punya target tinggi bersama Garuda.</p>" +
//                "<p>\"Semoga Timnas Indonesia bisa lolos ke Piala Dunia tentunya,\" kata Hubner ditanya soal target bersama Timnas Indonesia.</p>" +
//                "<p>\"Tentu, kami akan melakukan segalanya agar bisa lolos dan memenangkan pertandingan. Kami punya kesempatan yang besar,\" ujar Hubner menambahkan.</p>";
        txtTitle = findViewById(R.id.txtTitle);
        txtPublishedAt = findViewById(R.id.publishedAt);
        ivUrlToImage = findViewById(R.id.thumbnail);
        txtDescription = findViewById(R.id.desc);
        txtAuthor = findViewById(R.id.author);
        txtNewsContent = findViewById(R.id.newsContent);
        txtUrl=findViewById(R.id.url);
        bookmark=findViewById(R.id.bookmark);
//        newsContent.setText(Html.fromHtml(htmlText));
        txtTitle.setText(title);
        txtPublishedAt.setText(publishedAt);
        txtDescription.setText(description);
        txtAuthor.setText(author);
        txtUrl.setText("Link asli berita : \n" + url);
        Linkify.addLinks(txtUrl, Linkify.WEB_URLS);
        txtNewsContent.setText(content);
        Picasso.get().load(urlToImage).into(ivUrlToImage);
    }

    public void back(View view) {
        finish();
    }

    public void bookmarkClick(View view) {
        if (isBookmarked) {
            bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
            isBookmarked = false;
        } else {
            bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24);
            isBookmarked = true;
        }
    }
}