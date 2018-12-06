package com.example.rayjo_000.menu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class homescreen_add1 extends AppCompatActivity implements View.OnClickListener {
    private Button next_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen_add1);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        next_btn = (Button) findViewById(R.id.hmscreenadd_next);

        next_btn.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v.getId() == R.id.hmscreenadd_next){
            startActivity(new Intent(homescreen_add1.this, homescreen_add2.class));
        }
    }
}
