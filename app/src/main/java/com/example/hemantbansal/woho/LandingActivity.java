package com.example.hemantbansal.woho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

public class LandingActivity extends AppCompatActivity {

    String strin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        strin = "Landing Activity Launched";

        Toast.makeText(LandingActivity.this,strin,Toast.LENGTH_SHORT).show();



    }
}
