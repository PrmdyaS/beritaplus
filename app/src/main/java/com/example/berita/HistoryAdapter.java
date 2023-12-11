package com.example.berita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<String> historyList;

    public HistoryAdapter(List<String> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.historyTextView.setText(historyList.get(position));

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dapatkan konteks dari view yang di klik
                Context context = v.getContext();

                // Panggil metode deleteData dengan parameter position untuk menghapus item
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                databaseHelper.deleteData(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView historyTextView;
        ImageView btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            historyTextView = itemView.findViewById(R.id.history_text_view);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}