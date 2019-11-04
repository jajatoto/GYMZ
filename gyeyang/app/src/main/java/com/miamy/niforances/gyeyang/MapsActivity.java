package com.miamy.niforances.gyeyang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import net.daum.mf.map.gen.DaumMapLibraryAndroidMeta;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity {

    private ArrayList<Info> list;

    double lat;
    double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        MapView mapView = new MapView(this);

        mapView.setCalloutBalloonAdapter(new CustomCalloutBalloonAdapter());

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        list = (ArrayList<Info>) getIntent().getSerializableExtra("arrayList");

        for (int i = 0; i < list.size(); i++) {


            lat = Double.parseDouble(list.get(i).getRental_location_lat());
            lon = Double.parseDouble(list.get(i).getRental_location_lon());

            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(lat, lon);

            MapPOIItem marker = new MapPOIItem();
            marker.setItemName(list.get(i).getTitle()+"");
            marker.setTag(i);
            marker.setMapPoint(mapPoint);
            marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
            marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

            mapView.addPOIItem(marker);
            //Log.i("asdf",(i+1)+"번째 마커 추가.");

            mapView.setMapCenterPoint(mapPoint, true);
            mapView.setZoomLevel(1, true);
        }

        mapView.setPOIItemEventListener(new MapView.POIItemEventListener() {
            @Override
            public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
                Log.i("asdf","MarkerTouched");
            }

            @Override
            public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
            }

            @Override
            public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

                Log.i("asdfx","BalloonTouched");
                Intent intent = new Intent(getApplicationContext(), BalloonActivity.class);

                intent.putExtra("List", list.get(mapPOIItem.getTag()));

                startActivity(intent);
            }

            @Override
            public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {
            }
        });

    }

}