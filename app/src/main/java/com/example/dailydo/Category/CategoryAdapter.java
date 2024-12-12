package com.example.dailydo.Category;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dailydo.Category.Category;

import com.example.dailydo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

  private final Context context;
  private final List<Category> categoryList;

  public CategoryAdapter(Context context, List<Category> categoryList) {
    this.context = context;
    this.categoryList = categoryList;
  }

  public static class CategoryViewHolder extends RecyclerView.ViewHolder {
    TextView tvCategoryName;
    ImageView ivIcon;
    CardView cvCategory;

    public CategoryViewHolder(@NonNull View itemView) {
      super(itemView);
      cvCategory = itemView.findViewById(R.id.cvCategory);
      tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
      ivIcon = itemView.findViewById(R.id.ivIcon);
    }
  }

  @NonNull
  @Override
  public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(this.context)
        .inflate(R.layout.row_category, parent, false);
    return new CategoryViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
    Category category = categoryList.get(position);
    holder.tvCategoryName.setText(category.getCategoryName());
    Glide.with(context).load(category.getIcon()).into(holder.ivIcon);
    holder.cvCategory.setOnClickListener(v -> {
      FragmentActivity activity = (FragmentActivity) context;
      DetailCategoryFragment fragment = DetailCategoryFragment.newInstance(new ArrayList<>(category.getTasks()));

      activity.getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.fragment_container, fragment)
          .addToBackStack(null)
          .commit();
    });
  }


  @Override
  public int getItemCount() {
    return categoryList.size();
  }
}
