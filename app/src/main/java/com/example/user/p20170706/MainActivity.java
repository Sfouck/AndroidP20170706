package com.example.user.p20170706;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView myListView;
    private String[] items = {"Login","RPS"};
    private Activity[] activitys = {new LoginActivity(),new RpsActivity()};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,items);

        myListView = (ListView) findViewById(R.id.myListView);
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = myListView.getItemAtPosition(position);
                Toast.makeText(view.getContext(), listItem.toString(),Toast.LENGTH_SHORT).
                        show();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,activitys[position].getClass());
                startActivity(intent);
            }
        });
    }
}
