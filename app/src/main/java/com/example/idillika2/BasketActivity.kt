package com.example.idillika2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class BasketActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        val toolbar: Toolbar = findViewById(R.id.basket_toolbar)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            openClothesActivity()
        }
    }

    private fun openClothesActivity() {
        val intent = Intent(this, ClothesActivity::class.java)
        startActivity(intent)
    }
}


