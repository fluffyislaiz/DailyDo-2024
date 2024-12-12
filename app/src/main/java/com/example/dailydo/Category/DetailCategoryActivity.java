package com.example.dailydo.Category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailydo.R;

import java.util.ArrayList;
import java.util.List;

//public class DetailCategoryActivity extends AppCompatActivity {
//
//  private RecyclerView recyclerView;
//  private TaskAdapter taskAdapter;
//  private List<String> tasks;
//
//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    EdgeToEdge.enable(this);
//    setContentView(R.layout.activity_detail_category);
//
//    // Ambil data dari Intent
//    tasks = getIntent().getStringArrayListExtra("tasks");
//
//    if (tasks == null) {
//      tasks = new ArrayList<>(); // Berikan default jika data null
//    }
//
//    recyclerView = findViewById(R.id.rvTasks);
//    taskAdapter = new TaskAdapter(tasks);
//    recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    recyclerView.setAdapter(taskAdapter);
//
//  }
//}

class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
  private final List<String> taskList;

  TaskAdapter(List<String> taskList) {
    this.taskList = taskList;
  }

  static class TaskViewHolder extends RecyclerView.ViewHolder {
    TextView tvTaskName;

    TaskViewHolder(@NonNull View itemView) {
      super(itemView);
      tvTaskName = itemView.findViewById(R.id.tvTaskName);
    }
  }

  @NonNull
  @Override
  public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.row_task, parent, false);
    return new TaskViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
    holder.tvTaskName.setText(taskList.get(position));
  }

  @Override
  public int getItemCount() {
    return taskList.size();
  }
}


