package com.example.nissmo.estates;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.nissmo.estates.Adapters.EstateListAdapter;
import com.example.nissmo.estates.DBHandler.DBHandler;
import com.example.nissmo.estates.Structure.Estate;

import java.util.List;

/**
 * Created by nissmo on 22.04.17.
 */

public class ShowActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    List<Estate> estateList;
    ListView listView;
    private DBHandler db;
    EstateListAdapter adapter;

    Spinner spinner;
    private static final String[]sale = {"Изберете тип","продажба", "наем"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_estates);
        db = new DBHandler(this);

        spinner = (Spinner) findViewById(R.id.spinner3);
        final ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(ShowActivity.this,
                android.R.layout.simple_spinner_item,sale);
        spin_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spin_adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getItemAtPosition(position).toString()=="Изберете тип")
        {
            estateList = db.getAllEstates();
            listView = (ListView) findViewById(R.id.list_estates);
            adapter = new EstateListAdapter(this,estateList);
            listView.setAdapter(adapter);
        }
        estateList = db.getTypeEstates(parent.getItemAtPosition(position).toString());
        listView = (ListView) findViewById(R.id.list_estates);
        adapter = new EstateListAdapter(this,estateList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        estateList = db.getAllEstates();
        listView = (ListView) findViewById(R.id.list_estates);
        adapter = new EstateListAdapter(this,estateList);
        listView.setAdapter(adapter);
    }
}
