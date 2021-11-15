package com.example.Sharbny;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.Sharbny.Model.WaterIntake;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Home4 extends AppCompatActivity {
    private Button nextbtn4;
    private EditText age;
    private String agestring;
    private int ageint;
    Integer intage;
    Double doublekg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home3);
        nextbtn4 = (Button) findViewById(R.id.next4);
        age = (EditText) findViewById(R.id.enterage);

        nextbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agestring = age.getText().toString();
                ageint =Integer.parseInt(agestring);
                final DatabaseReference RootRef;
                RootRef = FirebaseDatabase.getInstance().getReference();

                RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        HashMap<String, Object> userdatamap = new HashMap<>();
                        userdatamap.put("Age", ageint);

                        Intent intent = getIntent();
                        final String message = intent.getStringExtra("message").toString();
                        final String kgstring = intent.getStringExtra("weight").toString();
                        double kgweightdouble =Double.parseDouble(kgstring);

                        RootRef.child("USERS").child(message).updateChildren(userdatamap);


                        String first = dataSnapshot.child(message).child("firstname").getValue(String.class);
                        String theusername = dataSnapshot.child(message).child("username").getValue(String.class);



                        WaterIntake a = new WaterIntake(first,theusername,ageint,kgweightdouble);
                        double the_intake = a.waterintakecalc(ageint,kgweightdouble);
                        userdatamap.put("intake", the_intake);
                        RootRef.child("USERS").child(message).updateChildren(userdatamap);




                        RootRef.child("USERS").child(message).updateChildren(userdatamap);



                        Intent intent1 = new Intent(Home4.this, Login.class);
                        startActivity(intent1);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }}