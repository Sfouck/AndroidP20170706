package com.example.user.p20170706;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText vUID;
    private EditText vPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vUID = (EditText) findViewById(R.id.editUserID);
        vPass = (EditText) findViewById(R.id.editPassword);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCheck(v);
            }
        });
    }

    private void doCheck(View view){


        Bundle bundle = new Bundle();
        bundle.putString("UserID", vUID.getText().toString());
        bundle.putString("Password", vPass.getText().toString());

        doSumbit(bundle);
    }

    private void doSumbit(Bundle bundle) {
        Intent intent = new Intent();

        intent.setClass(MainActivity.this, AfterLoginActivity.class);
        intent.putExtras(bundle);

        startActivityForResult(intent,0);
    }
}
