package com.example.user.p20170706;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AfterLoginActivity extends AppCompatActivity {
    TextView tvUserID;
    TextView tvPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        tvUserID = (TextView) findViewById(R.id.logedUserID);
        tvPassword = (TextView) findViewById(R.id.logedPassword);

        Bundle bundle = getIntent().getExtras();
        String sUserID = bundle.getString("UserID");
        String sPassword = bundle.getString("Password");

        tvUserID.setText(sUserID);
        tvPassword.setText(sPassword);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AfterLoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
