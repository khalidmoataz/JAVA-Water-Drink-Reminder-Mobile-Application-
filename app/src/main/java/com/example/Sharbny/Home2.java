package com.example.Sharbny;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Home2 extends AppCompatActivity {
    private Button malebtnchoice,femalebtnchoice,next2btn;
    public String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        malebtnchoice = (Button) findViewById(R.id.malechoice);
        femalebtnchoice = (Button) findViewById(R.id.femalechoice);
        next2btn = (Button) findViewById(R.id.next2);

        malebtnchoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference RootRef;
                RootRef= FirebaseDatabase.getInstance().getReference();
                malebtnchoice.setBackground( getResources().getDrawable(R.drawable.buttons));
                femalebtnchoice.setBackground( getResources().getDrawable(R.drawable.buttonlowlightorange));

                RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        HashMap<String,Object> userdatamap = new HashMap<>();
                        userdatamap.put("gender","male");

                        Intent intent = getIntent();
                        message = intent.getStringExtra("message").toString();
                        RootRef.child("USERS").child(message).updateChildren(userdatamap);





                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        femalebtnchoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                femalebtnchoice.setBackground( getResources().getDrawable(R.drawable.buttons));
                malebtnchoice.setBackground( getResources().getDrawable(R.drawable.buttonlowlightorange));
                final DatabaseReference RootRef;
                RootRef= FirebaseDatabase.getInstance().getReference();
                RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        HashMap<String,Object> userdatamap = new HashMap<>();
                        userdatamap.put("gender","female");
                         Intent intent = getIntent();
                         String message = intent.getStringExtra("message").toString();
                        RootRef.child("USERS").child(message).updateChildren(userdatamap);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        next2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home2.this, Home3.class);
                intent.putExtra("message",message);
                startActivity(intent);


            }
        });
    }
}
