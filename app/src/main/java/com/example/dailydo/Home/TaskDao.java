package com.example.dailydo.Home;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertTask(List<Task> wishlists);

  @Query("SELECT * FROM Task")
  List<Task> getAllTask();

  @Query("DELETE FROM Task WHERE idTask = :idTask")
  void deleteTask(String idTask);

  @Update
  void updateTask(Task task);

  @Query("SELECT * FROM Task WHERE idTask = :idTask")
  Task getTaskById(String idTask);
}
