package com.example.berita;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<Article> articles; // List data artikel
    private OnItemClickListener mListener;

    // Interface untuk listener
    public interface OnItemClickListener {
        void onItemClick(Article article, int position);
    }

    // Metode untuk menetapkan listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public NewsAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.textViewTitle.setText(article.getTitle());
        Picasso.get().load(article.getUrlToImage()).into(holder.imageView);

        holder.itemView.setOnClickListener(view -> {
            if (mListener != null && position != RecyclerView.NO_POSITION) {
                mListener.onItemClick(article, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewAdapter);
            textViewTitle = itemView.findViewById(R.id.textViewTitleAdapter);
        }
    }
}
