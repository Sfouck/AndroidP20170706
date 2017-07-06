package com.example.user.p20170706;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText vUID;
    private EditText vPass;
    private TextView tvError;
    private String targetUID = "admin";
    private String targetPassword = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vUID = (EditText) findViewById(R.id.editUserID);
        vPass = (EditText) findViewById(R.id.editPassword);
        tvError = (TextView) findViewById(R.id.tvError);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCheck(v);
            }
        });
    }

    private void doCheck(View view) {
        String sUID = vUID.getText().toString();
        String sPassword = vPass.getText().toString();

        Log.d("Main","doCheck");
        Log.d("Main","sUID = /" + sUID + "/");
        Log.d("Main","sPassword = /" + sPassword + "/");
        tvError.setText("");
        if (sUID.equals(targetUID) && sPassword.equals(targetPassword)) {
            Bundle bundle = new Bundle();
            bundle.putString("UserID", sUID);
            bundle.putString("Password", sPassword);
            doSumbit(bundle);
        }else if(sUID.equals("")){
            tvError.setText("帳號欄位不得為空白!");
        }else{
            tvError.setText("帳號或密碼輸入錯誤!");
        }
    }

    private void doSumbit(Bundle bundle) {
        Intent intent = new Intent();

        Log.d("Main","doSumbit");
        intent.setClass(MainActivity.this, AfterLoginActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
