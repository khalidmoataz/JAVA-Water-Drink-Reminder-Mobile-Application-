package com.example.Sharbny;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Home3 extends AppCompatActivity {
    private Button nextbtn3;
    private EditText kgweight;
    private int kgweightint;
    private String kgweightstring;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home4);
        nextbtn3 = (Button) findViewById(R.id.next3);
        kgweight = (EditText) findViewById(R.id.enterweight);

        nextbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kgweightstring = kgweight.getText().toString();
                kgweightint =Integer.parseInt(kgweightstring);
                final DatabaseReference RootRef;
                RootRef= FirebaseDatabase.getInstance().getReference();

                RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        HashMap<String,Object> userdatamap = new HashMap<>();
                        userdatamap.put("weight",kgweightint);

                        Intent intent = getIntent();
                        String message = intent.getStringExtra("message").toString();
                        RootRef.child("USERS").child(message).updateChildren(userdatamap);


                        Intent intent2 = new Intent(Home3.this, Home4.class);
                        intent2.putExtra("message",message);
                        intent2.putExtra("weight",kgweightstring);
                         startActivity(intent2);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }




}
