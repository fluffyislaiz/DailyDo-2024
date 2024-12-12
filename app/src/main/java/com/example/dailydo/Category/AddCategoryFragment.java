package com.example.dailydo.Category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dailydo.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.UUID;

public class AddCategoryFragment extends Fragment {
  private EditText editTextCategoryName;
  private Button buttonSave;

  public AddCategoryFragment() {}

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_add_category, container, false);

    editTextCategoryName = view.findViewById(R.id.editTextCategoryName);
    buttonSave = view.findViewById(R.id.buttonSave);

    buttonSave.setOnClickListener(v -> saveCategory());

    return view;
  }

  private void saveCategory() {
    String categoryName = editTextCategoryName.getText().toString().trim();
    if (!categoryName.isEmpty()) {
      String categoryId = UUID.randomUUID().toString();
      Category category = new Category(categoryId, categoryName, "", new ArrayList<>());

      DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("categories");
      databaseReference.child(categoryId).setValue(category)
              .addOnSuccessListener(aVoid -> {
                Toast.makeText(getContext(), "Kategori berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
              })
              .addOnFailureListener(e -> {
                Toast.makeText(getContext(), "Gagal menambahkan kategori: " + e.getMessage(), Toast.LENGTH_SHORT).show();
              });
    } else {
      Toast.makeText(getContext(), "Nama kategori tidak boleh kosong", Toast.LENGTH_SHORT).show();
    }
  }
}