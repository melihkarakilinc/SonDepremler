package com.melihkarakilinc.sondepremler;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Intent i;
    ArrayList<DepremInf> list = new ArrayList<DepremInf>();
    Double enlem;
    Double boylam;

    TextView txt_tarih_detay,txt_saat_detay,txt_derinlik_detay,txt_siddet_detay,txt_yer_detay,txt_tip_detay;
    Button btn_uydu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        txt_tarih_detay=findViewById(R.id.txt_tarih_map);
        txt_saat_detay=findViewById(R.id.txt_saat_map);
        txt_derinlik_detay=findViewById(R.id.txt_derinlik_map);
        txt_siddet_detay=findViewById(R.id.txt_siddet_map);
        txt_yer_detay=findViewById(R.id.txt_yer_map);
        txt_tip_detay=findViewById(R.id.txt_tip_map);
        btn_uydu=findViewById(R.id.btn_uydu);

        btn_uydu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL){

                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    btn_uydu.setText("NORMAL");
                }
                else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    btn_uydu.setText("UYDU");
                }
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        i = getIntent();


        int position = (Integer) i.getIntExtra("position",0);

        ArrayList<DepremInf> depreminfo = (ArrayList<DepremInf>) i.getSerializableExtra("name_of_extra");

        txt_tarih_detay.setText(depreminfo.get(position).getTarih());
        txt_saat_detay.setText(depreminfo.get(position).getSaat());
        txt_derinlik_detay.setText(depreminfo.get(position).getDerinlik());
        txt_siddet_detay.setText(depreminfo.get(position).getSiddet());
        txt_yer_detay.setText(depreminfo.get(position).getYer());
        txt_tip_detay.setText(depreminfo.get(position).getTip());
        Log.e("enlem",depreminfo.get(position).getEnlem());

        enlem = Double.parseDouble(depreminfo.get(position).getEnlem());
        boylam=Double.parseDouble(depreminfo.get(position).getBoylam());
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(enlem, boylam);
        mMap.addMarker(new MarkerOptions().position(sydney).title(depreminfo.get(position).getYer()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,8));
    }
}