package com.example.karsparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class bookActivity extends AppCompatActivity {
    Button conform;
    String name,email,address,ph,Vnum,Vtype;
    String TAG = "Booking";
    TextView nametv,phonetv,emailtv,addresstv,vehicleNumtv,vehicleTypetv;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        nametv = findViewById(R.id.etName);
        phonetv = findViewById(R.id.etPhone);
        emailtv = findViewById(R.id.editTextTextPersonName3);
        addresstv = findViewById(R.id.editTextTextPersonName4);
        vehicleNumtv = findViewById(R.id.mytxtVehicleNum);
        vehicleTypetv = findViewById(R.id.txtVehicleTypeNum);
//////////////////
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myReff = database.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        userID = user.getUid();

        FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d(TAG, "sucessfully signed_out");
                }
            }
        };

        myReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        conform = findViewById(R.id.conformBtn);
        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(bookActivity.this, "booking conformed", Toast.LENGTH_SHORT).show();
                finish();
                Intent i = new Intent(bookActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds: dataSnapshot.getChildren()){
            dataStorage uInfo = new dataStorage();
            uInfo.setName(ds.child(userID).getValue(dataStorage.class).getName());
            uInfo.setEmail(ds.child(userID).getValue(dataStorage.class).getEmail());
            uInfo.setPh(ds.child(userID).getValue(dataStorage.class).getPh());
            uInfo.setAddress(ds.child(userID).getValue(dataStorage.class).getAddress());
            uInfo.setVehicleNumber(ds.child(userID).getValue(dataStorage.class).getVehicleNumber());
            uInfo.setVehicleType(ds.child(userID).getValue(dataStorage.class).getVehicleType());

            name = uInfo.getName();
            email = uInfo.getEmail();
            ph = uInfo.getPh();
            address = uInfo.getAddress();
            Vnum = uInfo.getVehicleNumber();
            Vtype = uInfo.getVehicleType();

            nametv.setText(name);
            phonetv.setText(ph);
            emailtv.setText(email);
            addresstv.setText(address);
            vehicleNumtv.setText(Vnum);
            vehicleTypetv.setText(Vtype);
        }
    }
//////////////////////////

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(authStateListener);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if(authStateListener != null){
//            mAuth.removeAuthStateListener(authStateListener);
//        }
//    }

}