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


public class ChinesePage1GalleryPic1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chinese_page_1_gallery_pic_1, container, false);
        ImageView img = rootView.findViewById(R.id.imageView10);

        int width = 332;
        int height = 172;
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.dumplings2)).getBitmap();
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        img.setImageBitmap(bitmap);
        return rootView;
    }
}
