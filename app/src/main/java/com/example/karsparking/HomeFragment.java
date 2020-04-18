package com.example.karsparking;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import androidx.fragment.app.Fragment;

import com.example.karsparking.R;
import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.MapView;
        import com.google.android.gms.maps.MapsInitializer;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.model.CameraPosition;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;

public class HomeFragment extends Fragment implements OnMapReadyCallback{
    private GoogleMap mGoogleMap;
    private MapView mMapView;
    private View mView;

//publicHomeFragment(){
//
//}
//
//@Override
//publicvoidonCreate(BundlesavedInstanceState){
//super.onCreate(savedInstanceState);
//}

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        mView=inflater.inflate(R.layout.fragment_home,container,false);
        return mView;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        mMapView=(MapView)mView.findViewById(R.id.map);
        if(mMapView!=null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        MapsInitializer.initialize(getContext());
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);///////:)))))))))))))))))

        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.689247,-74.044502)).title("StatueofLiberty").snippet("I hope to go there"));
        CameraPosition Liberty=CameraPosition.builder().target(new LatLng(40.689247,-74.044502)).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));
    }
}


