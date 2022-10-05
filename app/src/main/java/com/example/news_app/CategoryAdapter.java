package com.example.news_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<Categories_Modal> category_modals;
    private Context context;
    private CategoryClickInterface categoryClickInterface;

    public CategoryAdapter(ArrayList<Categories_Modal> category_modals, Context context, CategoryClickInterface categoryClickInterface) {
        this.category_modals = category_modals;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.categories,parent,false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Categories_Modal categories_modal=category_modals.get(position);
        holder.category_text.setText(categories_modal.getCategory());
        Picasso.get().load(categories_modal.getCategoryImageURL()).into(holder.category_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryClickInterface.onCategoryClick(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return category_modals.size();
    }
    public interface CategoryClickInterface{
        void onCategoryClick(int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView category_text;
        private ImageView category_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category_image=itemView.findViewById(R.id.image_category);
            category_text=itemView.findViewById(R.id.text_category);
        }
    }
}
