package com.example.nissmo.estates;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private String TAG = ShowActivity.class.getSimpleName();
    List<Estate> estateList;
    ListView listView;
    Button button;
    private DBHandler db;
    EstateListAdapter listadapter;

    Spinner spinner;
    Spinner spinner2;
    private static final String[]sale = {"","продажба", "наем"};
    private static final String[]region = {"","Владиславово", "Възраждане","Левски","Чайка"};

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

        spinner2 = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(ShowActivity.this,
                android.R.layout.simple_spinner_item,region);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        button = (Button) findViewById(R.id.bt_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter = 0;
                String region = spinner2.getSelectedItem().toString();
                String type = spinner.getSelectedItem().toString();
                String selection = "";
                String[] arguments = new String[5];
                if(!(region==""))
                {
                    if(selection=="")
                        selection += "region LIKE '" + region+"'";
                    else
                        selection += " AND region LIKE '" + region+"'";
                    arguments[counter]= region;
                    counter++;
                }
                if(!(type==""))
                {
                    if(selection=="")
                        selection += "type LIKE '" + type+"'";
                    else
                        selection += " AND type LIKE '" + type+"'";
                    arguments[counter]= type;
                    counter++;
                }
                Log.e(TAG, selection);
                estateList = db.getTypeEstates(selection,arguments);

                listView = (ListView) findViewById(R.id.list_estates);
                listadapter = new EstateListAdapter(ShowActivity.this,estateList);
                listView.setAdapter(listadapter);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

}
