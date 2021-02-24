package com.example.idillika2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        button = findViewById(R.id.btn_rectangle);
        button.setOnClickListener(view -> openClothesActivity());
    }

    private void openClothesActivity() {
        Intent intent = new Intent(this, ClothesActivity.class);
        startActivity(intent);
    }
}