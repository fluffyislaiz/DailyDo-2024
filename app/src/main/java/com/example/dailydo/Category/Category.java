package com.example.dailydo.Category;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "Category")
public class Category {

  @PrimaryKey
  @ColumnInfo(name = "categoryId")
  @NonNull
  private String categoryId;

  @ColumnInfo(name = "categoryName")
  private String categoryName;

  @ColumnInfo(name = "icon")
  private String icon;

  @ColumnInfo(name = "tasks")
  private List<String> tasks;

  public Category(String categoryId, String categoryName, String icon, List<String> tasks) {
    super();
    this.categoryId = categoryId;
    this.categoryName = categoryName;
    this.icon = icon;
    this.tasks = tasks;
  }

  public Category() {}

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public List<String> getTasks() {
    return tasks;
  }

  public void setTasks(List<String> tasks) {
    this.tasks = tasks;
  }

}