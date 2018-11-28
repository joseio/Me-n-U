package com.example.rayjo_000.menu;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;

class MapRestaurantData {
    public BitmapDescriptor icon;
    public LatLng position;
    public String[] tags;
    public String name;

    public  MapRestaurantData(BitmapDescriptor icon, LatLng position, String[] tags, String name) {
        this.icon = icon;
        this.position = position;
        this.tags = tags;
        this.name = name;
    }
}