package com.example.Sharbny;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.Sharbny.Model.SetProgressBar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class LoggedIn extends AppCompatActivity {
    private Button thecup;
    private TextView totalcupsdesign,intakeview;
    public Double totaldrinkstring;
    private ProgressBar prg;
    public String message;
    double totalCupsofwater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        Intent intent = getIntent();
        message = intent.getStringExtra("message").toString();
        prg=(ProgressBar)findViewById(R.id.progressBar);
        totalcupsdesign = (TextView) findViewById(R.id.textView3);
        intakeview = (TextView) findViewById(R.id.textView7);
        thecup = (Button) findViewById(R.id.cup);

        final DatabaseReference RootRef1;
        RootRef1= FirebaseDatabase.getInstance().getReference("USERS");
        RootRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Double intakedouble = dataSnapshot.child(message).child("intake").getValue(Double.class);
                Double totcups = dataSnapshot.child(message).child("TotalCups").getValue(Double.class);
                Double intakedouble4digits = (double)Math.round(intakedouble * 10d) / 10d;
                Double totcups4digits = (double)Math.round(totcups * 10000d) / 10000d;

                String intakestring = intakedouble4digits.toString();
                String tocupsstring = totcups4digits.toString();
                totalcupsdesign.setText(tocupsstring+"/"+intakestring);
                intakeview.setText(intakestring+" L");
                double totalcups = dataSnapshot.child(message).child("TotalCups").getValue(Double.class);
                double  intakenum= dataSnapshot.child(message).child("intake").getValue(Double.class);
                int a = (int) Math.round((totalcups/intakenum)*100); // 3
                HashMap<String,Object> addcup = new HashMap<>();
                RootRef1.child(message).updateChildren(addcup);
                prg.setProgress(a);
                if(totalcups > intakenum)
                {
                    thecup.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        thecup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference CupRef;
                CupRef= FirebaseDatabase.getInstance().getReference();
                CupRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        totalCupsofwater= dataSnapshot.child("USERS").child(message).child("TotalCups").getValue(Double.class);
                        totalCupsofwater += 0.200;
                        HashMap<String,Object> addcup = new HashMap<>();
                        addcup.put("TotalCups",totalCupsofwater);
                        CupRef.child("USERS").child(message).updateChildren(addcup);


                        double totalcups = dataSnapshot.child("USERS").child(message).child("TotalCups").getValue(Double.class);
                        double intakenum = dataSnapshot.child("USERS").child(message).child("intake").getValue(Double.class);
                        SetProgressBar setprog = new SetProgressBar(totalcups,intakenum);

                        prg.setProgress(setprog.CalcPercentage());
                        if(totalcups > intakenum)
                        {
                            thecup.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}