package com.example.Sharbny;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Sharbny.Model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Login extends AppCompatActivity {
    private Button Login;
    private EditText usernamebtn,passwordbtn;
    private String parentDbname= "USERS";
    private TextView adminlink;
    private TextView notadmin;
    private FirebaseAuth mAuth;
    private String email;
     private String theusername,first,thepass;
    private String USERNAME , PASSWORD;
    Users the_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernamebtn =(EditText) findViewById(R.id.Username);
        passwordbtn =(EditText) findViewById(R.id.Password);
        Login = (Button) findViewById(R.id.login_btn);



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                USERNAME = usernamebtn.getText().toString();
                PASSWORD = passwordbtn.getText().toString();
                login(USERNAME,PASSWORD);





            }
        });


    }

    private void login(final String userneme,final  String passwords) {
        final DatabaseReference RootRef1;
        RootRef1= FirebaseDatabase.getInstance().getReference("USERS");
        RootRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(userneme).exists())
                {
                    first = dataSnapshot.child(userneme).child("firstname").getValue(String.class);
                    theusername = dataSnapshot.child(userneme).child("username").getValue(String.class);
                    thepass = dataSnapshot.child(userneme).child("password").getValue(String.class);
                    the_user = new Users(first,theusername,thepass);
                    HashMap<String,Object> userdatamap = new HashMap<>(); // create list with user info
                    userdatamap.put("username",userneme);

                    if(the_user.getUsername().equals(userneme) && the_user.getPassword().equals(passwords)) {

                        Intent intent=new Intent(Login.this,LoggedIn.class);
                        intent.putExtra("message",theusername);
                        startActivity(intent);

                    }
                    else
                        Toast.makeText(Login.this,"Wrong User OR Pass",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(Login.this,"User Not Exist",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
