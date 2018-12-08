package com.example.rayjo_000.menu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ChinesePage3 extends Fragment   {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chinese_page_3, container, false);
        ImageView img = rootView.findViewById(R.id.imageButton10);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChinesePage1Gallery.class);
                startActivity(intent);
            }
        });

        int width = 850;
        int height = 420;
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.chinese3)).getBitmap();
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        img.setImageBitmap(bitmap);
        return rootView;
    }
}
