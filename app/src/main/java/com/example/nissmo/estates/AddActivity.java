package com.example.nissmo.estates;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nissmo.estates.DBHandler.DBHandler;
import com.example.nissmo.estates.R;
import com.example.nissmo.estates.Structure.Estate;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by nissmo on 21.04.17.
 */

public class AddActivity extends AppCompatActivity {

    Spinner spinner;
    Spinner spinner2;
    private static final String[]sale = {"продажба", "наем"};
    private static final String[]region = {"Владиславово", "Възраждане","Левски","Чайка"};

    Button add_button;

    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_estate);

        db = new DBHandler(this);

        spinner = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddActivity.this,
                android.R.layout.simple_spinner_item,sale);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(AddActivity.this,
                android.R.layout.simple_spinner_item,region);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        final EditText t_price = (EditText) findViewById(R.id.add_price);
        final EditText t_floors = (EditText) findViewById(R.id.add_floors);
        final EditText t_rooms = (EditText) findViewById(R.id.add_rooms);
        final EditText t_area = (EditText) findViewById(R.id.add_area);
        final EditText t_text = (EditText) findViewById(R.id.add_text);
        add_button = (Button) findViewById(R.id.add_record);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String region = spinner2.getSelectedItem().toString();
                String type = spinner.getSelectedItem().toString();
                String price = t_price.getText().toString();
                String floors = t_floors.getText().toString();
                String rooms = t_rooms.getText().toString();
                String area = t_area.getText().toString();
                String text = t_text.getText().toString();
                if(region.isEmpty() || type.isEmpty() || price.isEmpty() || floors.isEmpty() || rooms.isEmpty() || area.isEmpty() || text.isEmpty())
                {
                    Toast.makeText(AddActivity.this,"Въведете всички полета!!!",LENGTH_LONG).show();

                }
                else {
                    db.addEstate(new Estate(region, type, price, floors, rooms, area, text));
                    Toast.makeText(AddActivity.this,"Записът е добавен!", LENGTH_LONG).show();
                    t_price.setText("");
                    t_floors.setText("");
                    t_rooms.setText("");
                    t_area.setText("");
                    t_text.setText("");

                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }


}
