package com.example.dailydo.Home;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
  private static AppDatabase instance;
  public abstract TaskDao taskDao();
  public static synchronized AppDatabase getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app-database")
          .allowMainThreadQueries()
          .build();
    }
    return instance;
  }
}
