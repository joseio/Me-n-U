package com.example.rayjo_000.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.SearchView;

public class homescreen_add1 extends Activity implements View.OnClickListener {
    private Button next_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen_add1);

        next_btn = (Button) findViewById(R.id.hmscreenadd_next);

        next_btn.setOnClickListener(this);
        final SearchView searchView = findViewById(R.id.pickrestaurantview);
        searchView.setQueryHint("Search for a restaurant");
    }

    public void onClick(View v){
        if(v.getId() == R.id.hmscreenadd_next){
            startActivity(new Intent(homescreen_add1.this, homescreen_add2.class));
        }
    }
}
