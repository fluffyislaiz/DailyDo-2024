package com.example.dailydo.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailydo.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailTaskFragment extends Fragment {

  private TextView tvNamaTask, tvDeadline;
  private EditText etNamaTask, etDeadline;
  private Button btSave;

  private DatabaseReference databaseReference;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_detail_task, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    tvNamaTask = view.findViewById(R.id.tvNamaTask);
    tvDeadline = view.findViewById(R.id.tvDeadline);

    if (getArguments() != null) {
      tvNamaTask.setText(getArguments().getString("namaTask"));
      tvDeadline.setText(getArguments().getString("deadline"));
    }

    etNamaTask = view.findViewById(R.id.etNamaTask);
    etDeadline = view.findViewById(R.id.etDeadline);
    btSave = view.findViewById(R.id.btSave);

    databaseReference = FirebaseDatabase.getInstance().getReference("tasks");

    btSave.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String idTask = getArguments().getString("idTask");
        String taskBaru = etNamaTask.getText().toString();
        String deadlineBaru = etDeadline.getText().toString();

        if (idTask != null && !taskBaru.isEmpty() && !deadlineBaru.isEmpty()) {
          Task updateTask = new Task(idTask, taskBaru, deadlineBaru);

          databaseReference.child(idTask).setValue(updateTask)
                  .addOnSuccessListener(aVoid -> {
                    tvNamaTask.setText(taskBaru);
                    tvDeadline.setText(deadlineBaru);

                    etNamaTask.setText("");
                    etDeadline.setText("");

                    Toast.makeText(requireContext(), "Task berhasil diperbarui", Toast.LENGTH_SHORT).show();
                  })
                  .addOnFailureListener(e -> {
                    Toast.makeText(requireContext(), "Gagal memperbarui task", Toast.LENGTH_SHORT).show();
                  });
        } else {
          Toast.makeText(requireContext(), "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
      }
    });
  }
}