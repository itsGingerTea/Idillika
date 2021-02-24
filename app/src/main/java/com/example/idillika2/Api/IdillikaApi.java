package com.example.idillika2.Api;

import com.example.idillika2.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IdillikaApi {

    @GET("catalogList.php?section=21&session_id=f3e82db3d0b2bcce07eae17dd9cb46d3")
    Call<List<Item>> getItems();
}
