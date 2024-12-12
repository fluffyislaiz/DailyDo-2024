package com.example.dailydo.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dailydo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
  public HomeFragment() {}

  private RecyclerView recyclerView;
  private TaskAdapter taskAdapter;
  private List<Task> taskList;
  AppDatabase appDatabase;
  DatabaseReference reference;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_home, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    recyclerView = view.findViewById(R.id.rvTask);
    recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

    taskList = new ArrayList<>();
    taskAdapter = new TaskAdapter(requireContext(), taskList);
    recyclerView.setAdapter(taskAdapter);

    reference = FirebaseDatabase.getInstance().getReference("tasks");
    reference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        taskList.clear();
        if (snapshot.exists()) {
          for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
            Task task = dataSnapshot.getValue(Task.class);
            taskList.add(task);
          }
        } else {
          fetchDataFromApi();
        }
        taskAdapter.notifyDataSetChanged();
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {

      }
    });
  }

  private void fetchDataFromApi() {
    Thread thread = new Thread(() -> {
      try {
        URL url = new URL("http://10.0.2.2/apiTask.php");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
          response.append(line);
        }
        reader.close();

        Gson gson = new Gson();
        List<Task> tasks = gson.fromJson(response.toString(), new TypeToken<List<Task>>(){}.getType());

        saveDataToFirebase(tasks);
        new Handler(Looper.getMainLooper()).post(() -> {
          taskList.clear();
          taskList.addAll(tasks);
          taskAdapter.notifyDataSetChanged();
        });
      } catch (Exception e) {
        Log.e("HomeFragment", "Error fetching tasks", e);
      }
    });
    thread.start();
  }

  private void saveDataToFirebase(List<Task> tasks) {
    for (Task task : tasks) {

      String randomKey = reference.push().getKey();
      if (randomKey != null) {
        Task newTask = new Task(randomKey, task.getNamaTask(), task.getDeadline());
        reference.child(randomKey).setValue(newTask);
      }
    }
  }
}