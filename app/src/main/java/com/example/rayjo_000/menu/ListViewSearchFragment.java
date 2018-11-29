package com.example.rayjo_000.menu;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListViewSearchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_list_view_search, viewGroup, false);
        ArrayList<Integer> dishImages = this.getArguments().getIntegerArrayList("dishImages");

        List<Bitmap> drawableList = new ArrayList<>();
        for (Integer img : dishImages) {
            int width = 169;
            int height = 126;
            Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(img)).getBitmap();
            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
            drawableList.add(bitmap);
        }
        setListViewImages(view, drawableList);
        return view;
    }

    private void setListViewImages(View view, List<Bitmap> dishImages) {
        ImageView img = null;

        if (dishImages.size() == 1) {
            img = view.findViewById(R.id.imageView);
            img.setVisibility(View.VISIBLE);
            img.setImageBitmap(dishImages.get(0));
        } else if (dishImages.size() == 2) {
            img = view.findViewById(R.id.imageView);
            img.setVisibility(View.VISIBLE);
            img.setImageBitmap(dishImages.get(0));
            img = view.findViewById(R.id.imageView2);
            img.setVisibility(View.VISIBLE);
            img.setImageBitmap(dishImages.get(1));
        } else if (dishImages.size() == 3) {
            img = view.findViewById(R.id.imageView);
            img.setVisibility(View.VISIBLE);
            img.setImageBitmap(dishImages.get(0));
            img = view.findViewById(R.id.imageView2);
            img.setVisibility(View.VISIBLE);
            img.setImageBitmap(dishImages.get(1));
            img = view.findViewById(R.id.imageView3);
            img.setVisibility(View.VISIBLE);
            img.setImageBitmap(dishImages.get(2));
        } else if (dishImages.size() == 4) {
            img = view.findViewById(R.id.imageView);
            img.setVisibility(View.VISIBLE);
            img.setImageBitmap(dishImages.get(0));
            img = view.findViewById(R.id.imageView2);
            img.setVisibility(View.VISIBLE);
            img.setImageBitmap(dishImages.get(1));
            img = view.findViewById(R.id.imageView3);
            img.setVisibility(View.VISIBLE);
            img.setImageBitmap(dishImages.get(2));
            img = view.findViewById(R.id.imageView4);
            img.setVisibility(View.VISIBLE);
            img.setImageBitmap(dishImages.get(3));
        }
        else if (dishImages.size() == 5) {
            img = view.findViewById(R.id.imageView);
            img.setVisibility(View.VISIBLE);
            img.setImageBitmap(dishImages.get(0));
            img = view.findViewById(R.id.imageView2);
            img.setVisibility(View.VISIBLE);
            img.setImageBitmap(dishImages.get(1));
            img = view.findViewById(R.id.imageView3);
            img.setVisibility(View.VISIBLE);
            img.setImageBitmap(dishImages.get(2));
            img = view.findViewById(R.id.imageView4);
            img.setVisibility(View.VISIBLE);
            img.setImageBitmap(dishImages.get(3));
            img = view.findViewById(R.id.imageView5);
            img.setVisibility(View.VISIBLE);
            img.setImageBitmap(dishImages.get(4));
        }
    }
}