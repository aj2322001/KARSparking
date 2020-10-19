package com.example.karsparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class bookActivity extends AppCompatActivity {
    TextView name;
    String nameStr;
    DatabaseReference myReff;
    Button conform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

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
}