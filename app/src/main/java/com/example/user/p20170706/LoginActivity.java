package com.example.user.p20170706;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private EditText vUID;
    private EditText vPass;
    private TextView tvError;
    private CheckBox cbPassword;
    private final String shareLoginData = "shareLoginData";
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intinComponent();
        loadData();

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryLogin(v);
            }
        });
    }

    private void intinComponent() {
        vUID = (EditText) findViewById(R.id.editUserID);
        vPass = (EditText) findViewById(R.id.editPassword);
        tvError = (TextView) findViewById(R.id.tvError);
        cbPassword = (CheckBox) findViewById(R.id.cbPassword);
    }

    private void tryLogin(View view) {
        String sUID = vUID.getText().toString();
        String sPassword = vPass.getText().toString();

        CheckLogin check = new CheckLogin(sUID, sPassword);
        tvError.setText("");
        switch (check.doCheck()) {
            case CheckLogin.ALL_CHECKED:
                saveData();
                Bundle bundle = new Bundle();
                bundle.putString("UserID", sUID);
                bundle.putString("Password", sPassword);
                doSumbit(bundle);
                break;
            case CheckLogin.UID_MISS:
                tvError.setText("帳號欄位不得為空白!");
                break;
            case CheckLogin.INPUT_ERROR:
                tvError.setText("帳號或密碼輸入錯誤!");
                break;
        }
    }

    private void doSumbit(Bundle bundle) {
        Intent intent = new Intent();

        Log.d("Main", "doSumbit");
        intent.setClass(LoginActivity.this, AfterLoginActivity.class);
        intent.putExtras(bundle);

        finish();
        startActivity(intent);
    }

    private void saveData() {
        settings = getSharedPreferences(shareLoginData, MODE_PRIVATE);
        settings.edit()
                .putString("UserID", vUID.getText().toString())
                .putString("Password", cbPassword.isChecked() ? vPass.getText().toString() : "")
                .putString("cbPassword", cbPassword.isChecked() ? "True" : "False")
                .apply();
    }

    private void loadData() {
        settings = getSharedPreferences(shareLoginData, MODE_PRIVATE);
        vUID.setText(settings.getString("UserID", ""));
        vPass.setText(settings.getString("Password", ""));
        String checkPass = settings.getString("cbPassword", "");
        switch (checkPass) {
            case "True":
                cbPassword.setChecked(true);
                break;
            case "False":
                cbPassword.setChecked(false);
                break;
        }
    }
}
