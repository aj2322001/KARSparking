package com.example.karsparking;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;

public class HomeFragment extends Fragment implements OnMapReadyCallback{

    private  View mView;
    private MapView mMapView;
    private static Double latitude,longitude;
    private static LatLng latLng;
    private Location location;
    private Marker mMarker;
    private GoogleMap mGoogleMap;
    private LatLngBounds mMapBoundry;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        mView = inflater.inflate(R.layout.fragment_home,container,false);
        mMapView = (MapView) mView.findViewById(R.id.map);

        initGoogleMap(savedInstanceState);
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


