package com.example.dailydo.Category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dailydo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryFragment extends Fragment {
  public CategoryFragment() {}
;
  private RecyclerView recyclerView;
  private List<Category> categoryList = new ArrayList<>();
  private CategoryAdapter adapter;
  private AppDatabase appDatabase;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_category, container, false);

    recyclerView = view.findViewById(R.id.rvCategory);
    adapter = new CategoryAdapter(requireContext(), categoryList);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

    // Initialize database instance
    appDatabase = Room.databaseBuilder(requireContext(), AppDatabase.class, "dailydo-db")
        .fallbackToDestructiveMigration()
        .build();

    FloatingActionButton fabAddCategory = view.findViewById(R.id.fabAddCategory);
    fabAddCategory.setOnClickListener(v -> {
      FragmentManager fragmentManager = getParentFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      fragmentTransaction.replace(R.id.fragment_container, new AddCategoryFragment());
      fragmentTransaction.addToBackStack(null);
      fragmentTransaction.commit();
    });

    fetchCategories();
    return view;
  }

  private void fetchCategories() {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("categories");

    databaseReference.get().addOnCompleteListener(task -> {
      if (task.isSuccessful() && task.getResult().exists()) {
        categoryList.clear();
        for (DataSnapshot snapshot : task.getResult().getChildren()) {
          Category category = snapshot.getValue(Category.class);
          if (category != null) {
            categoryList.add(category);
          }
        }
        adapter.notifyDataSetChanged();
      } else {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/apiCategory.php")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.getCategories();

        call.enqueue(new Callback<ResponseBody>() {
          @Override
          public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful() && response.body() != null) {
              try {
                String json = response.body().string();
                Gson gson = new Gson();

                Category[] categories = gson.fromJson(json, Category[].class);

                for (Category category : categories) {
                  databaseReference.child(category.getCategoryId()).setValue(category);
                }

                categoryList.clear();
                categoryList.addAll(Arrays.asList(categories));
                adapter.notifyDataSetChanged();

              } catch (Exception e) {
                e.printStackTrace();
                Log.e("CategoryFragment", "Parsing error: " + e.getMessage());
              }
            } else {
              Log.e("CategoryFragment", "Response failed: " + response.message());
            }
          }

          @Override
          public void onFailure(Call<ResponseBody> call, Throwable t) {
            Log.e("CategoryFragment", "Error: " + t.getMessage());
            requireActivity().runOnUiThread(() ->
                    Toast.makeText(getContext(), "Gagal mengambil data", Toast.LENGTH_SHORT).show());
          }
        });
      }
    });
  }
}