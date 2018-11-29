package com.example.rayjo_000.menu;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;

class MapRestaurantData {
    public int image;
    public BitmapDescriptor icon;
    public LatLng position;
    public String[] tags;
    public String name;

    public  MapRestaurantData(int image, BitmapDescriptor icon, LatLng position, String[] tags, String name) {
        this.image = image;
        this.icon = icon;
        this.position = position;
        this.tags = tags;
        this.name = name;
    }

}