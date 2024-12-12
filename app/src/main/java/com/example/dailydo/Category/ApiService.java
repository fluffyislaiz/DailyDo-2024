package com.example.dailydo.Category;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
  @GET("apiCategory.php")
  Call<ResponseBody> getCategories();
}
