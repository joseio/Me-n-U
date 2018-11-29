package com.example.rayjo_000.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.content.Intent;


public class AddMenuItem_RateIt extends Activity implements View.OnClickListener {

    private Button finish_button_addmenuitemrating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_item__rate_it);

        finish_button_addmenuitemrating = (Button) findViewById(R.id.buttonfinishaddmenuitemrating);

        finish_button_addmenuitemrating.setOnClickListener(this);

        /*Add in Oncreate() function after setContentView()*/
        RatingBar simpleRatingBar1 = (RatingBar) findViewById(R.id.ratingBaraddmenuitemrating); // initiate a rating bar
        Float ratingNumber = simpleRatingBar1.getRating(); // get rating number from a rating bar


        /*Add in Oncreate() function after setContentView()*/
        RatingBar simpleRatingBar2 = (RatingBar) findViewById(R.id.ratingBaraddmenuitemrating); // initiate a rating bar
        int numberOfStars = simpleRatingBar2.getNumStars(); // get total number of stars of rating bar

    }

    public void onClick(View v)  {
        if(v.getId() == R.id.buttonfinishaddmenuitemrating)  {
            startActivity(new Intent(AddMenuItem_RateIt.this, MainActivity.class));
        }
    }

}
