package com.example.Sharbny;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home1 extends AppCompatActivity {

    private Button letsgobtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home1);
        letsgobtn = (Button) findViewById(R.id.letsgo1);

        letsgobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home1.this,signup.class);
                startActivity(intent);
            }
        });

    }
}
