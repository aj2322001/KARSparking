package com.example.karsparking;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import static android.content.Context.LOCATION_SERVICE;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment implements OnMapReadyCallback{

    private  View mView;
    private static String latStr,lngStr;
    private MapView mMapView;
    private static LatLng latLng;
    private GoogleMap mGoogleMap;
    private static double latitude,longitude;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        mView = inflater.inflate(R.layout.fragment_home,container,false);
        mMapView = (MapView) mView.findViewById(R.id.map);

        initGoogleMap(savedInstanceState);

//        Bundle fragmentbundle = getArguments();
//        latStr = fragmentbundle.getString("CurrentLatitude");
//        lngStr = fragmentbundle.getString("CurrentLongitude");

        return mView;
    }

    private void setCameraView(){
////causing application to crash
//        latitude = (Double)MainActivity.lat;
//        longitude = (Double)MainActivity.lng;
//
//        if( (latitude!=null) || (longitude!=null) ){
//            latLng = new LatLng(latitude,longitude);
//            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,0));
//        }
//        else {
            latLng = new LatLng(20.0507854,64.4174241);           //India's location
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,0));
//            target (latitude/longitude location), bearing, tilt, and zoom.
//        }

    }


    private void initGoogleMap(Bundle savedInstanceState){
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMyLocationEnabled(true);
        mGoogleMap = map;
        setCameraView();
        map.isTrafficEnabled();
        map.setOnMyLocationButtonClickListener(onMyLocationButtonClickListener);

//        spots(map);
//        latitude = Double.parseDouble(latStr);
//        longitude = Double.parseDouble(lngStr);
//        map.addMarker(new MarkerOptions()
//                .position(new LatLng(latitude, longitude))
//                .title("hi"));
    }

    private GoogleMap.OnMyLocationButtonClickListener onMyLocationButtonClickListener =
            new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            Pspots(mGoogleMap);
                        }
                    }, 2000 );//time in milisecond

                    return false;
                }
            };

    private void Pspots(GoogleMap map){
        double[][] loc = {{28.6233063,77.2993313},{28.6235956,77.2977739},{28.6190774,77.2950357},{28.6283398,77.2964182},
                {26.9111784,75.8177333},{26.9355417,75.7898209},{26.8573126,75.8265123},
                {28.4883482,77.2842267},{28.574814,77.3535236},{28.6452207,77.3202766},{28.6505072,77.3389033},
                {15.817382,78.0378173},{17.3671919,78.4744211},{17.4320092,78.4884994},{15.8267443,78.0331354},
                {28.4525346,77.5241227},{29.4739561,77.7137128},{29.4730142,77.7130033},
                {28.4176455,77.604351},{28.452778,77.5240206},{28.4641335,77.5056993},{28.4637939,77.5050258}};
        String[] placeName = {"EDMC Parking","Dimple Travels Two Wheeler Parking","Parking 3","Car parking",
                "Parking -Albert Hall Museum","INOX City Plaza","world trade park",
                "Parking - Surajkund Mela","Noida City Center Metro Parking","Kaushambi Metro Station Car Parking","GDA Multilevel parking",
                "GGH Parking","SYJ Complex Car Parking","Ap Tourism Parking Lot","CITY SQUARE MALL PARKING",
                "The Grand Venice Mall","SUPER 99","ASJ Grand Plaza Mall",
                "bhati parking yard","Valet Parking","Parking OFFICE","Basement parking"};
        for(int i=0 ; i<loc.length ; i++){
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(loc[i][0],loc[i][1]))
                        .title(placeName[i]));
        }
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}


