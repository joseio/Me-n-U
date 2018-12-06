package com.example.rayjo_000.menu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.SearchView;
import android.widget.Switch;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private MapRestaurantData[] allRestaurants;
    private ArrayList<MapRestaurantData> visibleRestaurants;
    private List<Marker> allMarkers;

    private ListViewSearchFragment listViewSearchFragment;
    private boolean inMapViewMode = true;
    private List<Integer> dishImages = new ArrayList<>();

    //TODO: Need to allow the list view to be searchable...right now, the search doesn't filter anything in list view

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

        listViewSearchFragment = new ListViewSearchFragment();

        final FragmentManager fragManager = getSupportFragmentManager();
        final SupportMapFragment mapFragment = (SupportMapFragment) fragManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initAllRestaurants();

        visibleRestaurants = new ArrayList<>();
        Collections.addAll(visibleRestaurants, allRestaurants);

        //Fetch all dish images when search view blank
        dishImages.clear();
        for (MapRestaurantData myData : allRestaurants) {
            dishImages.add(myData.image);
        }

        Switch switch1 = findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Bundle bundle = new Bundle();
                if (isChecked) {

                    mapFragment.getView().setVisibility(View.INVISIBLE);

                    Bundle dishImageBundle = new Bundle();
                    dishImageBundle.putIntegerArrayList("dishImages", new ArrayList<>(dishImages));
                    listViewSearchFragment.setArguments(dishImageBundle);

                    fragManager.beginTransaction()
                            .add(R.id.fragment_container, listViewSearchFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                    inMapViewMode = false;

                } else {
                    fragManager.beginTransaction()
                            .remove(listViewSearchFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mapFragment.getView().setVisibility(View.VISIBLE);
                        }
                    }, 200);

                    inMapViewMode = true;
                }
            }
        });


        final SearchView searchView = findViewById(R.id.maptextview);
        searchView.setOnClickListener(new SearchView.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });

        //TODO: Need to allow the list view to be searchable...right now, the search doesn't filter anything in list view

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String query) {
//              //Empty for now
                //TODO: Show drop-down suggestions as we type

                if (TextUtils.isEmpty(query)) {
                    visibleRestaurants = new ArrayList<>();
                    Collections.addAll(visibleRestaurants, allRestaurants);

                    //Fetch all dish images when search view blank
                    dishImages.clear();
                    for (MapRestaurantData myData : allRestaurants) {
                        dishImages.add(myData.image);
                    }
                    setMapMarkers();

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    return true;
                }
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                visibleRestaurants = new ArrayList<>();

                System.out.println(query);
                boolean once = false;
                dishImages.clear();
                // check if searching tag or name
                for (MapRestaurantData data : allRestaurants) {

                    for (int i = 0; i < data.tags.length; i++) {
                        if (data.tags[i].contains(query.toLowerCase())) {
                            visibleRestaurants.add(data);
                            if (!once)
                                for (MapRestaurantData myData : allRestaurants) {
                                    //Find all the restaurants w/ "pizza" tag, for example
                                    System.out.println("Now finding all restaurants w/ specified tag!");
                                    if (ArrayUtils.contains(myData.tags, query.toLowerCase())) {
                                        System.out.println(myData.name + " has a matching tag!");
                                        dishImages.add(myData.image);
                                    }
                                }
                            once = true;
                            break;
                        }
                    }
                }

                setMapMarkers();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });
    }


    private void initAllRestaurants() {
        allRestaurants = new MapRestaurantData[]{
                // Cravings
                new MapRestaurantData(R.drawable.dumplings, getBitmapWithSize(R.drawable.dumplings, 130, 130),
                        new LatLng(40.111280, -88.229047),
                        new String[]{
                                "chinese",
                                "dumplings",
                                "cravings",
                        },
                        "Cravings"),
                // Lai Lai Wok
                new MapRestaurantData(R.drawable.orangechicken, getBitmapWithSize(R.drawable.orangechicken, 130, 130),
                        new LatLng(40.110395, -88.233304),
                        new String[]{
                                "chinese",
                                "orange chicken",
                                "lai lai wok",
                        },
                        "Lai Lai Wok"),
                // Hot Wok Express
                new MapRestaurantData(R.drawable.chinese3, getBitmapWithSize(R.drawable.chinese3, 130, 130),
                        new LatLng(40.116486, -88.222531),
                        new String[]{
                                "chinese",
                                "chow mein",
                                "hot wok express",
                        },
                        "Hot Wok Express"),
                // Blaze Pizza
                new MapRestaurantData(R.drawable.pizza1, getBitmapWithSize(R.drawable.pizza1, 130, 130),
                        new LatLng(40.109224, -88.227177),
                        new String[]{
                                "pizza",
                                "blaze pizza"
                        },
                        "Blaze Pizza"),
                // Rosati's Pizza
                new MapRestaurantData(R.drawable.pizza2, getBitmapWithSize(R.drawable.pizza2, 120, 120),
                        new LatLng(40.106520, -88.221736),
                        new String[]{
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

    @Override
    public boolean onMarkerClick(Marker marker) {
        startActivity(new Intent(this, RestaurantMenuActivity.class));
        return false;
    }

    public void setMapMarkers() {
        allMarkers = new ArrayList<>();
        mMap.clear();
        for (MapRestaurantData data : visibleRestaurants) {
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .icon(data.icon)
                    .title(data.name)
                    .position(data.position));
            marker.setTag(data);
            allMarkers.add(marker);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        setMapMarkers();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(40.108881, -88.227209)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                for(Marker marker : allMarkers) {
                    if(Math.abs(marker.getPosition().latitude - latLng.latitude) < 0.001 && Math.abs(marker.getPosition().longitude
                        - latLng.longitude) < 0.001) {
                        System.out.println("Marker name = " + marker.getTitle() + " Marker lat = " + Math.abs(marker.getPosition().latitude));
                        marker.showInfoWindow();
                        break;
                    }
                }
            }
        });
    }
}
