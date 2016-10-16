package com.example.hemantbansal.woho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LandingActivity extends AppCompatActivity {

    String strin;
    Button loginBtn;
    EditText phn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        strin = "Landing Activity Launched";
        loginBtn=(Button)findViewById(R.id.loginbtn);
            phn=(EditText)findViewById(R.id.phoneinput);
        Toast.makeText(LandingActivity.this,strin,Toast.LENGTH_SHORT).show();

        loginBtn.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!(phn.getText().toString().equals(""))){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                MessageAPI.getJSONFromUrl();
                                startActivity(new Intent(LandingActivity.this, CheckOTP.class));
                            }
                        }).start();
                    }
                    else{
                        UtilClass.toastS(LandingActivity.this,"Enter Phone No.");
                    }

                    }
                }
        );



    }
}
