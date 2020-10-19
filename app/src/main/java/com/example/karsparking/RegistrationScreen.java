package com.example.karsparking;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationScreen extends AppCompatActivity {

    TextView nameet,emailet,vehicleNoet,phoneet,addresset,passwordet,passwordRechecket,toSignIntv;
    Button btnSignUp;
    Spinner vehicleTypeS;
    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String name, email,vehicleNumber,ph,address,pwd,pwdre,vehicleType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        mFirebaseAuth = FirebaseAuth.getInstance();
        nameet = findViewById(R.id.etNameR);
        emailet = findViewById(R.id.etEmailR);
        vehicleNoet = findViewById(R.id.etVehicleNumberR);
        phoneet = findViewById(R.id.etPhoneNoR);
        addresset = findViewById(R.id.etAddressR);
        passwordet = findViewById(R.id.etPasswordR);
        passwordRechecket = findViewById(R.id.etPasswordRecheckR);
        btnSignUp = findViewById(R.id.btnSignupR);
        toSignIntv = findViewById(R.id.tvToSigninR);
        vehicleTypeS = findViewById(R.id.spinnerVehicleTypeR);

//        if(mFirebaseAuth.getCurrentUser() != null){
//            startActivity(new Intent(RegistrationScreen.this, MainActivity.class));
//            finish();
//        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameet.getText().toString();
                email = emailet.getText().toString();
                vehicleNumber = vehicleNoet.getText().toString();
                ph = phoneet.getText().toString();
                address = addresset.getText().toString();
                pwd = passwordet.getText().toString();
                pwdre = passwordRechecket.getText().toString();
                vehicleType = vehicleTypeS.getSelectedItem().toString();

                    if (name.isEmpty()) {
                        nameet.setError("Please enter your name");
                        nameet.requestFocus();
                    } else if (email.isEmpty()) {
                        emailet.setError("Please enter your email id");
                        emailet.requestFocus();
                    } else if (vehicleNumber.isEmpty()) {
                        vehicleNoet.setError("Please enter your vehicle number");
                        vehicleNoet.requestFocus();
                    } else if (ph.isEmpty()) {
                        phoneet.setError("Please enter your phone number");
                        phoneet.requestFocus();
                    } else if (address.isEmpty()) {
                        addresset.setError("Please enter your address");
                        addresset.requestFocus();
                    } else if (pwd.isEmpty()) {
                        passwordet.setError("Please enter your password");
                        passwordet.requestFocus();
                    }
                    else if (pwd.length() <=9){
                        passwordet.setError("password must be more than 8 characters");
                        passwordet.requestFocus();
                    }
                    else if (pwdre.isEmpty()) {
                        passwordRechecket.setError("Please enter password again");
                        passwordRechecket.requestFocus();
                    } else if (!(pwdre.contentEquals(pwd))) {
                        passwordRechecket.setError("password doesn't match password");
                        passwordRechecket.requestFocus();
                    } else if (vehicleType.contentEquals("Select")) {
                        ((TextView) vehicleTypeS.getSelectedView()).setError("Please enter vehicleType");
                        vehicleTypeS.requestFocus();
                    }

//                else if(name.isEmpty() && email.isEmpty() && vehicleNumber.isEmpty() && ph.isEmpty() && address.isEmpty() && pwd.isEmpty() && pwdre.isEmpty()){
//                    Toast.makeText(RegistrationScreen.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
//                }
                    else if ((!(email.isEmpty() && pwd.isEmpty()))) {
                        mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(RegistrationScreen.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    database = FirebaseDatabase.getInstance();
                                    myRef = database.getReference("USER");
                                    dataStorage data = new dataStorage();
                                    data.setName(name);
                                    data.setPh(ph);
                                    data.setEmail(email);
                                    data.setAddress(address);
                                    data.setVehicleType(vehicleType);
                                    data.setVehicleNumber(vehicleNumber);
                                    myRef.push().setValue(data);
                                    finish();
                                    startActivity(new Intent(RegistrationScreen.this, MainActivity.class));
                                } else {
                                    Toast.makeText(RegistrationScreen.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(RegistrationScreen.this, "Error Occurred!", Toast.LENGTH_SHORT).show();

                    }
        }
        });

        toSignIntv.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
//                finish();
                Intent intLogIn = new Intent(RegistrationScreen.this,Login.class);
                Pair[] pairsReg = new Pair[3];
                pairsReg[0]= new Pair<View , String>(emailet,"emailTrans");
                pairsReg[1]= new Pair<View , String>(passwordet,"passwordTrans");
                pairsReg[2]= new Pair<View , String>(btnSignUp,"logSignTrans");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegistrationScreen.this,pairsReg);
                startActivity(intLogIn,options.toBundle());
                finish();
            }
        });
    }
}
