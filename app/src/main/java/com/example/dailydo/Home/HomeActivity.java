package com.example.dailydo.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dailydo.Category.CategoryActivity;
import com.example.dailydo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

//public class HomeActivity extends AppCompatActivity {
//
//  BottomNavigationView botNavbar;
//  RecyclerView recyclerView;
//  TaskAdapter taskAdapter;
//  List<Task> taskList;
//
//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    EdgeToEdge.enable(this);
//    setContentView(R.layout.activity_home);
//
//    recyclerView = findViewById(R.id.rvTask);
//    recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//    taskList = new ArrayList<>();
//    taskAdapter = new TaskAdapter(this, taskList);
//    recyclerView.setAdapter(taskAdapter);
//
//    taskAdapter = new TaskAdapter(this, taskList);
//    recyclerView.setAdapter(taskAdapter);
//
//    botNavbar= findViewById(R.id.bottomNav);
//    botNavbar.setSelectedItemId(R.id.home);
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
//
//    Thread thread = new Thread(new Runnable() {
//      @Override
//      public void run() {
//        try {
//          URL url = new URL("http://10.0.2.2/ApiDailyDo/apiTask.php");
//          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//          connection.setRequestMethod("GET");
//
//          BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//          StringBuilder response = new StringBuilder();
//          String line;
//          while ((line = reader.readLine()) != null) {
//            response.append(line);
//          }
//          reader.close();
//
//          Gson gson = new Gson();
//          List<Task> users = gson.fromJson(response.toString(), new TypeToken<List<Task>>(){}.getType());
//
//          new Handler(Looper.getMainLooper()).post(() -> {
//            taskList.clear();
//            taskList.addAll(users);
//            taskAdapter.notifyDataSetChanged();
//          });
//
//        } catch (Exception e) {
//          Log.e("HomeActivity", "Error fetching tasks", e);
//        }
//      }
//    });
//    thread.start();
//  }
//}

public class HomeActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    BottomNavigationView botNavbar;
    botNavbar= findViewById(R.id.bottomNav);
    botNavbar.setSelectedItemId(R.id.home);
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
          .replace(R.id.fragment_container, new HomeFragment())
          .commit();
    }
  }
}