package com.melihkarakilinc.sondepremler;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class AllMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Intent i;
    ArrayList<DepremInf> depreminfo = new ArrayList<DepremInf>();
    ArrayList<LatLng> arrayList = new ArrayList<LatLng>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_maps);


        i = getIntent();

        depreminfo = (ArrayList<DepremInf>) i.getSerializableExtra("listem");

        Log.e("deneme",depreminfo.get(0).getEnlem());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for (int i=0 ; i < depreminfo.size();i++){
            LatLng latLng = new LatLng(Double.parseDouble(depreminfo.get(i).getEnlem()),Double.parseDouble(depreminfo.get(i).getBoylam()));
           arrayList.add(latLng);
        }

        for (int i=0 ; i < depreminfo.size();i++){
            mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(depreminfo.get(i).getYer()+" "+ depreminfo.get(i).getSiddet()));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
        }

        mMap.animateCamera(CameraUpdateFactory.zoomTo(5));
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                Toast.makeText(AllMapsActivity.this,"Maker Tıklandı",Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
    }
}