package com.example.marketplace;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Market extends MainActivity {
    TextView T1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        setSupportActionBar(findViewById(R.id.toolbar));

        T1 = findViewById(R.id.t1);
        String welcome = getString(R.string.welcome);
        String user = getIntent().getExtras().getString("user");
        if (user != "") {
            welcome += " " + user;
        }
        T1.setText(welcome);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInf = getMenuInflater();
        menuInf.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.search):
                showToast("Search");
                break;
            case (R.id.singOut):

                //TODO: realizar sing out
                showToast("Sign Out");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openVegetables(View view) {
        Intent vegetables = new Intent(view.getContext(), vegetables.class);
        startActivity(vegetables);
    }

    public void openFruits(View view) {
        Intent fruits = new Intent(view.getContext(), fruits.class);
        startActivity(fruits);
    }

}