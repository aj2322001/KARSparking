package com.example.karsparking;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private View mView;
    private static String latStr, lngStr;
    private MapView mMapView;
    private static LatLng latLng;
    private GoogleMap mGoogleMap;
    private static double latitude, longitude;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private FusedLocationProviderClient fusedLocationProviderClient;

    Button Book;
//    FragmentContainerView fragHforBook;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        mMapView = (MapView) mView.findViewById(R.id.map);    //imp

        initGoogleMap(savedInstanceState);

//        Bundle fragmentbundle = getArguments();
//        latStr = fragmentbundle.getString("CurrentLatitude");
//        lngStr = fragmentbundle.getString("CurrentLongitude");

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Book = mView.findViewById(R.id.book);
        Book.setVisibility(View.INVISIBLE);
//        fragHforBook = mView.findViewById(R.id.fragment_container_home);
//        fragHforBook.setVisibility(View.INVISIBLE);


        Book.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
//                Book.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(),"you are booking",Toast.LENGTH_SHORT).show();
//                fragHforBook.setVisibility(View.VISIBLE);
//                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
//                bookFragment elf = new bookFragment();
//                ft.replace(R.id.booking, elf);
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                ft.addToBackStack(null);
//                ft.commit();
                Intent intent = new Intent(getActivity(), bookActivity.class);
                Pair[] pairsCon = new Pair[1];
                pairsCon[0]= new Pair<View , String>(Book,"conformBtn");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),pairsCon);
                startActivity(intent,options.toBundle());
            }
        });
    }

    private void setCameraView() {
////causing application to crash
//        latitude = (Double)MainActivity.lat;
//        longitude = (Double)MainActivity.lng;
//
//        if( (latitude!=null) || (longitude!=null) ){
//            latLng = new LatLng(latitude,longitude);
//            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,0));
//        }
//        else {
        latLng = new LatLng(20.0507854, 64.4174241);           //India's location
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 0));
//            target (latitude/longitude location), bearing, tilt, and zoom.
//        }

    }


    private void initGoogleMap(Bundle savedInstanceState) {
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
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
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
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Book.setVisibility(View.VISIBLE);
                return false;
            }
        });
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


