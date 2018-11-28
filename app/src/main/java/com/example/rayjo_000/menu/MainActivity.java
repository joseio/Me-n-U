package com.example.rayjo_000.menu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapRestaurantData[] allRestaurants;
    private ArrayList<MapRestaurantData> visibleRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                startActivity(new Intent(MainActivity.this, homescreen_add1.class));
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        initAllRestaurants();
        visibleRestaurants = new ArrayList<>();
        Collections.addAll(visibleRestaurants, allRestaurants);

        final EditText searchField = findViewById(R.id.maptextview);
        searchField.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    visibleRestaurants = new ArrayList<>();

                    String searchText = v.getText().toString();
                    if (searchText.equals("")) {
                        Collections.addAll(visibleRestaurants, allRestaurants);
                    }

                    // check if searching tag or name
                    for (MapRestaurantData data : allRestaurants) {
                        for (int i = 0; i < data.tags.length; i++) {
                            if (data.tags[i].equals(searchText)) {
                                visibleRestaurants.add(data);
                                break;
                            }
                        }
                    }
                    setMapMarkers();

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
    }

    private void initAllRestaurants() {
        allRestaurants = new MapRestaurantData[] {
                // Cravings
                new MapRestaurantData(getBitmapWithSize(R.drawable.dumplings,130, 130),
                        new LatLng(40.111280, -88.229047),
                        new String[] {
                                "chinese",
                                "dumplings",
                                "cravings",
                        },
                        "Cravings"),
                // Lai Lai Wok
                new MapRestaurantData(getBitmapWithSize(R.drawable.orangechicken,130, 130),
                        new LatLng(40.110395, -88.233304),
                        new String[] {
                                "chinese",
                                "orange chicken",
                                "lai lai wok",
                        },
                        "Lai Lai Wok"),
                // Hot Wok Express
                new MapRestaurantData(getBitmapWithSize(R.drawable.chinese3,130, 130),
                        new LatLng(40.116486, -88.222531),
                        new String[] {
                                "chinese",
                                "chow mein",
                                "hot wok express",
                        },
                        "Hot Wok Express"),
                // Blaze Pizza
                new MapRestaurantData(getBitmapWithSize(R.drawable.pizza1,130, 130),
                        new LatLng(40.109224, -88.227177),
                        new String[] {
                                "pizza",
                                "blaze pizza"
                        },
                        "Blaze Pizza"),
                // Rosati's Pizza
                new MapRestaurantData(getBitmapWithSize(R.drawable.pizza2,120, 120),
                        new LatLng(40.106520, -88.221736),
                        new String[] {
                                "pizza",
                                "rosati's pizza"
                        },
                        "Rosati's Pizza"),
        };
    }

    private BitmapDescriptor getBitmapWithSize(int image, int width, int height) {
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(image)).getBitmap();
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setMapMarkers() {
        mMap.clear();
        for (MapRestaurantData data : visibleRestaurants) {
            mMap.addMarker(new MarkerOptions().icon(data.icon).position(data.position));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        setMapMarkers();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(40.108881, -88.227209)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }
}
