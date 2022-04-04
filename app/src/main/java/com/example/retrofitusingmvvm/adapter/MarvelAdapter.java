package com.example.retrofitusingmvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitusingmvvm.R;
import com.example.retrofitusingmvvm.databinding.MarvelItemBinding;
import com.example.retrofitusingmvvm.model.MarvelModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MarvelAdapter extends RecyclerView.Adapter<MarvelAdapter.MyViewHolder> {

    Context context;
    List<MarvelModel> marvelModelList;

    public MarvelAdapter(Context context, List<MarvelModel> marvelModelList) {
        this.context = context;
        this.marvelModelList = marvelModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MarvelItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.marvel_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MarvelModel model = marvelModelList.get(position);
        if (model != null) {
            try {
                holder.bind(position);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public int getItemCount() {
        return marvelModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        MarvelItemBinding itemBinding;

        public MyViewHolder(@NonNull MarvelItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(int position) {
            itemBinding.nameTxt.setText(marvelModelList.get(position).getTitle());
            // Glide.with(context).load(marvelModelList.get(position).getThumbnailUrl()).into(itemBinding.imageView);
            Picasso.get().load(marvelModelList.get(position).getThumbnailUrl()).into(itemBinding.imageView);
        }
    }
}
