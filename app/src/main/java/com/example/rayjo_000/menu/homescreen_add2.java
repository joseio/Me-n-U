package com.example.rayjo_000.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.Button;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;





public class homescreen_add2 extends Activity {

    private static final int PICK_IMAGE_REQUEST = 100;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen_add2);

        iv = (ImageView) findViewById(R.id.imgView);

        Button finish_btn = (Button)findViewById(R.id.hmscreen_addf);

        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homescreen_add2.this, AddMenuItem_RateIt.class));
            }
        });
    }


    public void loadImagefromGallery(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case PICK_IMAGE_REQUEST:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    // method 1
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        iv.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
    }

}



