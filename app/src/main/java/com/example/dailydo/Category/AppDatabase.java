package com.example.dailydo.Category;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Category.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase{

  private static AppDatabase instance;

  public abstract CategoryDao categoryDao();

  public static synchronized AppDatabase getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app-database")
          .allowMainThreadQueries()
          .build();
    }
    return instance;
  }

}