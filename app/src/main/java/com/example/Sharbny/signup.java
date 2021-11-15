package com.example.Sharbny;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class signup extends AppCompatActivity {
    private Button createaccount;
    private EditText Inputfirstname,Username,Email,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        createaccount = (Button) findViewById(R.id.signmeup); //get id from design
        Inputfirstname =(EditText) findViewById(R.id.First_Name_btn);
        Username =(EditText) findViewById(R.id.User_Name_btn);
        Email =(EditText) findViewById(R.id.email_btn);
        Password =(EditText) findViewById(R.id.password_btn);

        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Event on click sign up button
                CreateAccount ();



            }
        });
    }

    private  void  CreateAccount()
    {
        String namefirst =Inputfirstname.getText().toString(); // convert inputs to string
        String username = Username.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        if(TextUtils.isEmpty(namefirst)) {
            Toast.makeText(this, "Please Enter Your Firstname", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(username))
        {
            Toast.makeText(this, "Please Enter Your username", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {

            Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(email))
        {

            Toast.makeText(this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
        }
        else
        {
            validate(namefirst,username,password,email);

        }


    }

    private void validate(final String namefirst, final String username, final String password,final String email) {

        final DatabaseReference RootRef; // create a firebase database reference
        RootRef= FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                    if(!(dataSnapshot.child("USERS").child(username).exists())) // check if username exist or not
                {

                    HashMap<String,Object> userdatamap = new HashMap<>(); // create list with user info
                    userdatamap.put("username",username);
                    userdatamap.put("password",password);
                    userdatamap.put("firstname",namefirst);
                    userdatamap.put("email",email);
                    userdatamap.put("TotalCups",0);


                    RootRef.child("USERS").child(username).updateChildren(userdatamap) // update database with userinfo list
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())  //check if signing up success
                                    {

                                        Intent intent = new Intent(signup.this, Home2.class); // go to next page
                                        intent.putExtra("message",username); // send username to the next page to complete register
                                        startActivity(intent);

                                    }
                                    else
                                    {
                                        Toast.makeText(signup.this,"Failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(signup.this,"This",Toast.LENGTH_SHORT);
                    Intent intent=new Intent(signup.this,MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
