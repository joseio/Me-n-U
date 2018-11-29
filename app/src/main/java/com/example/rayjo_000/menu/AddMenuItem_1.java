package com.example.rayjo_000.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class AddMenuItem_1 extends Activity implements View.OnClickListener {

    private Button next_button_addmenuitem1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_item_1);

        next_button_addmenuitem1 = (Button) findViewById(R.id.buttonnextaddmenuitem1);

        next_button_addmenuitem1.setOnClickListener(this);

    }


    public void onClick(View v)  {
        if(v.getId() == R.id.buttonnextaddmenuitem1)  {
            startActivity(new Intent(AddMenuItem_1.this, AddMenuItem_2.class));
        }
    }

}
