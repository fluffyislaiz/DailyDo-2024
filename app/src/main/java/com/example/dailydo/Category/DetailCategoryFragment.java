package com.example.dailydo.Category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailydo.R;

import java.util.ArrayList;
import java.util.List;

public class DetailCategoryFragment extends Fragment {
  public DetailCategoryFragment() {}

  private RecyclerView recyclerView;
  private TaskAdapter taskAdapter;
  private List<String> tasks;
  TextView tvCategoryName;

  public static DetailCategoryFragment newInstance(ArrayList<String> tasks) {
    DetailCategoryFragment fragment = new DetailCategoryFragment();
    Bundle args = new Bundle();
    args.putStringArrayList("tasks", tasks);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_detail_category, container, false);

    tvCategoryName = view.findViewById(R.id.tvCategoryName);

    if (getArguments() != null) {
      tasks = getArguments().getStringArrayList("tasks");
    }

    if (tasks == null) {
      tasks = new ArrayList<>();
    }

    recyclerView = view.findViewById(R.id.rvTasks);
    taskAdapter = new TaskAdapter(tasks);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setAdapter(taskAdapter);

    return view;
  }
}