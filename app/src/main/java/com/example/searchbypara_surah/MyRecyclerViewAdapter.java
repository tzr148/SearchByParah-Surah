package com.example.searchbypara_surah;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class myRecyclerViewAdapter extends RecyclerView.Adapter<myRecyclerViewAdapter.MyVH> {

    List<Verse> verses;
    public myRecyclerViewAdapter(List<Verse> verses) {
        this.verses = verses;
    }

    @NonNull
    @Override
    public myRecyclerViewAdapter.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item, parent, false);
        return new MyVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myRecyclerViewAdapter.MyVH holder, int position) {
        holder.data=verses.get(position);
        holder.txtText.setText(holder.data.getText());
        holder.txtTranslation.setText(String.valueOf(holder.data.getTranslation()));
    }

    @Override
    public int getItemCount() {
        return verses.size();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        TextView txtText;
        TextView txtTranslation;
        Verse data;
        public MyVH(@NonNull View itemView) {
            super(itemView);
            txtText = itemView.findViewById(R.id.arabic);
            txtTranslation = itemView.findViewById(R.id.translation);
        }
    }
}
