package com.example.rayjo_000.menu;

import android.app.Activity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class AddMenuItem_2 extends AppCompatActivity implements View.OnClickListener {

    private Button next_button_addmenuitem2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_item_2);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        next_button_addmenuitem2 = (Button) findViewById(R.id.buttonnextaddmenuitem2);

        next_button_addmenuitem2.setOnClickListener(this);

    }


    public void onClick(View v)  {
        if(v.getId() == R.id.buttonnextaddmenuitem2)  {
            startActivity(new Intent(AddMenuItem_2.this, AddMenuItem_3.class));
        }
    }


}
