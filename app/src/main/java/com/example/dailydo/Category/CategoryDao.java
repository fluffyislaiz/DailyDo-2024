package com.example.dailydo.Category;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertCategories(List<Category> categories);

  @Insert
  void insertCategory(Category category);

  @Query("SELECT * FROM Category")
  List<Category> getAllCategories();



}