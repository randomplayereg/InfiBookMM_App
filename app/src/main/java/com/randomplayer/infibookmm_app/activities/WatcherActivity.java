package com.randomplayer.infibookmm_app.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.randomplayer.infibookmm_app.R;

public class WatcherActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;

    private Button btnNext;
    private Button btnPrev;

    private Boolean globalWatch;
//    private ArrayList<ArrayList<Transaction>> transactionBundle;

    private int totalTrans;
    private int lookedAt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watcher);

//        totalTrans = Transaction.TransactionBundle.get(0).size() + Transaction.TransactionBundle.get(1).size();
//        lookedAt = 0;
//        btnNext = findViewById(R.id.btn_next); btnNext.setOnClickListener(this);
//        btnPrev = findViewById(R.id.btn_prev); btnPrev.setOnClickListener(this);
//
//        Intent intent = getIntent();
//        globalWatch = (boolean)intent.getExtras().get("Global");
//        if (globalWatch){
//            // TODO: resolve putextyra an ArrayList
//            //transactionBundle = (ArrayList)intent.getExtras().get("TransactionBundle");
//            String raw = "";
//            raw += "Ready: " + "\r\n";
//            int count = 0;
//            for (Transaction tr : Transaction.TransactionBundle.get(0)){
//                count++;
//                raw += String.valueOf(count) + ". " + tr.getBook() + "\r\n";
//            }
//            raw += "Await: " + "\r\n";
//            count = 0;
//            for (Transaction tr :  Transaction.TransactionBundle.get(1)){
//                count++;
//                raw += String.valueOf(count) + ". " + tr.getBook() + "\r\n";
//            }
//            Toast.makeText(this, raw, Toast.LENGTH_LONG).show();
//        }
//        else{
//            RelativeLayout topTool = findViewById(R.id.top_tool);
//            topTool.setVisibility(View.GONE);
//            String raw = "";
//            raw = raw + "lender_lat" + String.valueOf(intent.getExtras().get("lender_lat")) + "\r\n";
//            raw = raw + "lender_lng" + intent.getBundleExtra("lender_lng") + "\r\n";
//            raw = raw + "borrower_lat" + intent.getBundleExtra("borrower_lat") + "\r\n";
//            raw = raw + "borrower_lng" + intent.getBundleExtra("borrower_lng") + "\r\n";
//            Toast.makeText(this, raw, Toast.LENGTH_LONG).show();
//        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng station = getCurrentUserLocation();

        mMap.clear();
        mMap.addMarker(new MarkerOptions()
                .position(station)
                .title("Station")
                .snippet("You are here!")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        );
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(station, 12.0f));

//        if (globalWatch){
//            for (Transaction tr : Transaction.TransactionBundle.get(0)){
//                LatLng user = new LatLng(tr.getBorrower().getLat(), tr.getBorrower().getLng());
//                mMap.addMarker(new MarkerOptions()
//                        .position(user)
//                        .title(tr.getBorrower().getEmail())
//                        .snippet("Book is ready to be taken...")
//                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
//                );
//                createDashedLine(mMap, station, user, Color.GREEN);
//            }
//            for (Transaction tr :  Transaction.TransactionBundle.get(1)){
//                LatLng user = new LatLng(tr.getLender().getLat(), tr.getLender().getLng());
//                mMap.addMarker(new MarkerOptions()
//                        .position(new LatLng(tr.getLender().getLat(), tr.getLender().getLng()))
//                        .title(tr.getLender().getEmail())
//                        .snippet("Book has not yet arrived!")
//                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
//                );
//                createDashedLine(mMap, station, user, Color.YELLOW);
//            }
//        }
//        else{
//
//        }
    }

    private LatLng getCurrentUserLocation(){
        return new LatLng(10.776874, 106.689194); // YOKO
    }

    public void createDashedLine(GoogleMap map, LatLng latLngOrig, LatLng latLngDest, int color){
        double difLat = latLngDest.latitude - latLngOrig.latitude;
        double difLng = latLngDest.longitude - latLngOrig.longitude;

        double zoom = map.getCameraPosition().zoom;

        double divLat = difLat / (zoom * 2);
        double divLng = difLng / (zoom * 2);

        LatLng tmpLatOri = latLngOrig;

        for(int i = 0; i < (zoom * 2); i++){
            LatLng loopLatLng = tmpLatOri;

            if(i > 0){
                loopLatLng = new LatLng(tmpLatOri.latitude + (divLat * 0.25f), tmpLatOri.longitude + (divLng * 0.25f));
            }

            Polyline polyline = map.addPolyline(new PolylineOptions()
                    .add(loopLatLng)
                    .add(new LatLng(tmpLatOri.latitude + divLat, tmpLatOri.longitude + divLng))
                    .color(color)
                    .width(5f));

            tmpLatOri = new LatLng(tmpLatOri.latitude + divLat, tmpLatOri.longitude + divLng);
        }
    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btn_next:
//                lookedAt++;
//                if (lookedAt < 0) lookedAt = totalTrans - 1;
//                if (lookedAt >= totalTrans) lookedAt = 0;
//                showUserOnMap(lookedAt);
//                break;
//            case R.id.btn_prev:
//                lookedAt--;
//                if (lookedAt < 0) lookedAt = totalTrans - 1;
//                if (lookedAt >= totalTrans) lookedAt = 0;
//                showUserOnMap(lookedAt);
//                break;
//        }
    }

//    private void showUserOnMap(int lookedAt) {
//        if (lookedAt + 1 <= Transaction.TransactionBundle.get(0).size()) {
//            LatLng location = new LatLng(
//                    Transaction.TransactionBundle.get(0).get(lookedAt).getBorrower().getLat(),
//                    Transaction.TransactionBundle.get(0).get(lookedAt).getBorrower().getLng()
//            );
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 12.0f));
//        }
//        else{
//            LatLng location = new LatLng(
//                    Transaction.TransactionBundle.get(1).get(lookedAt - Transaction.TransactionBundle.get(0).size()).getLender().getLat(),
//                    Transaction.TransactionBundle.get(1).get(lookedAt - Transaction.TransactionBundle.get(0).size()).getLender().getLng()
//            );
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 12.0f));
//        }
//    }


}