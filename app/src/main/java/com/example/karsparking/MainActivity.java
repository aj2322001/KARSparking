package com.example.karsparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button logout;
    TextView text;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.button);
        text = findViewById(R.id.textView5);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if(mFirebaseAuth.getCurrentUser() != null){
//            startActivity(new Intent(MainActivity.this, MainActivity.class));
//            finish();
//        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFirebaseAuth.getCurrentUser() != null){
                    mFirebaseAuth.signOut();
                }
                finish();

//                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
    }
}
