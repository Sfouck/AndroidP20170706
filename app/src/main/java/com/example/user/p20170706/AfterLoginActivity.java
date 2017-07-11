package com.example.user.p20170706;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AfterLoginActivity extends AppCompatActivity {
    private TextView tvUserID;
    private TextView tvPassword;
    private ListView lvHistory;
    private SharedPreferences settings;
    private ArrayList<Date> history;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        initCompent();
        saveData();
        reloadList();

        Bundle bundle = getIntent().getExtras();

        tvUserID.setText(bundle.getString("UserID"));
        tvPassword.setText(bundle.getString("Password"));

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterLoginActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    private void initCompent() {
        tvUserID = (TextView) findViewById(R.id.logedUserID);
        tvPassword = (TextView) findViewById(R.id.logedPassword);
        lvHistory = (ListView) findViewById(R.id.lvHistory);
        settings = getSharedPreferences(tvUserID.getText().toString(), MODE_PRIVATE);
    }

    private void saveData() {
        Date date = new Date();
        String old = settings.getString("history", "");
        String dataHistory = old + " " + String.valueOf(date.getTime());
        Log.d("saveData","saveDataEdit");
        Log.d("saveData",dataHistory);
        settings.edit()
                .putString("history", dataHistory.trim())
                .apply();
    }

    private void loadData() {
        String[] data = settings.getString("history", "").split(" ");
        history = new ArrayList<Date>();
        Log.d("loadData","loadDataAdd");
        for (String time : data) {
            history.add(new Date(Long.parseLong(time)));
        }
    }

    private void reloadList() {
        loadData();
        DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH點mm分");
        ArrayList<String> dateList = new ArrayList<String>();
        for (Date date:history){
            dateList.add(dateFormat.format(date));
        }


        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,dateList);
        lvHistory.setAdapter(adapter);
        lvHistory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AfterLoginActivity.this,parent.getSelectedItem().toString(),Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
