package com.example.dailydo.Category;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dailydo.Home.HomeActivity;
import com.example.dailydo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

//public class CategoryActivity extends AppCompatActivity {
//
//  BottomNavigationView botNavbar;
//  RecyclerView recyclerView;
//  RecyclerView.LayoutManager layoutManager;
//  List<Category> categoryList = new ArrayList<>();
//  CategoryAdapter adapter;
//
//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    EdgeToEdge.enable(this);
//    setContentView(R.layout.activity_category);
//
//    List<Category> categoryList = new ArrayList<>();
//    categoryList.add(new ListCategory("Sekolah"));
//    categoryList.add(new ListCategory("Kantor"));
//    categoryList.add(new ListCategory("Rumah Sakit"));
//    categoryList.add(new ListCategory("Restoran"));
//    categoryList.add(new ListCategory("Toko"));
//
//    recyclerView = findViewById(R.id.rvCategory);
//    adapter = new CategoryAdapter(getApplicationContext(),categoryList);
//    recyclerView.setAdapter(adapter);
//    layoutManager = new GridLayoutManager(CategoryActivity.this, 2);
//    recyclerView.setLayoutManager(layoutManager);
//
//    fetchCategories();
//
//    botNavbar= findViewById(R.id.bottomNav);
//    botNavbar.setSelectedItemId(R.id.category);
//    botNavbar.setOnItemSelectedListener(item -> {
//      if (item.getItemId() == R.id.home){
//        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//        finish();
//      } else if(item.getItemId() == R.id.category){
//        startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
//        finish();
//      }
//      return false;
//    });
//  }
//
//
//  private void fetchCategories() {
//    new Thread(() -> {
//      Retrofit retrofit = new Retrofit.Builder()
//          .baseUrl("http://10.0.2.2/ApiDailyDo/")
//          .addConverterFactory(GsonConverterFactory.create())
//          .build();
//
//      ApiService apiService = retrofit.create(ApiService.class);
//      Call<List<Category>> call = apiService.getCategories();
//
//      call.enqueue(new Callback<List<Category>>() {
//        @Override
//        public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
//          if (response.isSuccessful() && response.body() != null) {
//            categoryList.clear();
//            categoryList.addAll(response.body());
//            runOnUiThread(() -> adapter.notifyDataSetChanged());
//          } else {
//            Log.e("CategoryActivity", "Response failed: " + response.message());
//          }
//        }
//
//        @Override
//        public void onFailure(Call<List<Category>> call, Throwable t) {
//          Log.e("CategoryActivity", "Error: " + t.getMessage());
//          runOnUiThread(() -> Toast.makeText(CategoryActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show());
//        }
//      });
//    }).start();
//  }
//}

public class CategoryActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_category);
    BottomNavigationView botNavbar;

    botNavbar= findViewById(R.id.bottomNav);
    botNavbar.setSelectedItemId(R.id.category);
    botNavbar.setOnItemSelectedListener(item -> {
      if (item.getItemId() == R.id.home){
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
      } else if(item.getItemId() == R.id.category){
        startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
        finish();
      }
      return false;
    });

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.fragment_container, new CategoryFragment())
          .commit();
    }
  }
}