package com.example.idillika2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.idillika2.Api.App.getIdillikaApi;

public class ClothesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Item> items;
    private SharedPreferences sharedPref;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);

        sharedPref = getSharedPreferences("is_favorite", Context.MODE_PRIVATE);

        toolbar = findViewById(R.id.clothes_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> openMainActivity());

        recyclerView = findViewById(R.id.rv_clothes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        items = new ArrayList<>();

        Runnable runnable = () -> getIdillikaApi().getItems().enqueue(new Callback<List<Item>>() {

            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful() && !thread.isInterrupted()) { //когда вызывается interrupt(), поток не убивается. Если надо прервать,
                    items = response.body();                              //то проверяем если !isInterrupted() - то просто не выполняем все что внутри
                    assert items != null;
                    for (Item i : items) {
                        i.setFavoriteInPrefs(loadState(i.getId())); //фоновый поток
                    }

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(() -> {
                        LikesCallback callback = (like, position) -> {
                            //Активити знает только то что случился лайк и в связи с этим надо что-то делать
                            saveState(items.get(position).getId(), like);
                            Item newItem = items.get(position); //хранит позицию шмотки
                            newItem.setFavoriteInPrefs(like); //меняет булевое значение в шмотке(меняем всю шмотку читай)
                            adapter.notifyItemChanged(position, newItem);
                        };

                        adapter = new ClothesAdapter(items, callback);
                        recyclerView.setAdapter(adapter);
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

            }
        });

        thread = new Thread(runnable);
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.interrupt();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.basket_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btn_basket) {
            openBasketActivity();
            return true;
        } else return super.onOptionsItemSelected(item);
    }

    private void openBasketActivity() {
        Intent intent = new Intent(this, BasketActivity.class);
        startActivity(intent);
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveState(int id, boolean isFavorite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(String.valueOf(id), isFavorite);
        editor.apply();
    }

    public boolean loadState(int id) {
        return sharedPref.getBoolean(String.valueOf(id), false);
    }
}