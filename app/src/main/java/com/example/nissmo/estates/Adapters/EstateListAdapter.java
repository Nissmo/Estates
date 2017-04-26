package com.example.nissmo.estates.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.nissmo.estates.DBHandler.DBHandler;
import com.example.nissmo.estates.R;
import com.example.nissmo.estates.Structure.Estate;

import java.util.List;

/**
 * Created by nissmo on 22.04.17.
 */

public class EstateListAdapter extends ArrayAdapter {
    private final Context context;
    private final List<Estate> estateList;
    private DBHandler db;
    public EstateListAdapter(Context context, List<Estate> estate) {
        super(context, R.layout.layout_list_estate, estate);
        this.context = context;
        this.estateList = estate;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        db = new DBHandler(context);
        View rowView = inflater.inflate(R.layout.layout_list_estate, parent, false);
        TextView textView1 = (TextView) rowView.findViewById(R.id.show_region);
        TextView textView2 = (TextView) rowView.findViewById(R.id.show_type);
        TextView textView3 = (TextView) rowView.findViewById(R.id.show_price);
        TextView textView4 = (TextView) rowView.findViewById(R.id.show_area);
        TextView textView5 = (TextView) rowView.findViewById(R.id.show_rooms);
        TextView textView6 = (TextView) rowView.findViewById(R.id.show_foolrs);
        TextView textView7 = (TextView) rowView.findViewById(R.id.show_text);
        textView1.setText(estateList.get(position).getRegion());
        textView2.setText(estateList.get(position).getType());
        textView3.setText(estateList.get(position).getPrice());
        textView4.setText(estateList.get(position).getArea());
        textView5.setText(estateList.get(position).getRooms());
        textView6.setText(estateList.get(position).getFloors());
        textView7.setText(estateList.get(position).getText());

//        Button button_delete = (Button) rowView.findViewById(R.id.bt_delete);
//        button_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                db.deleteStudent(studentslist.get(position));
//                ((StudentActivity)context).recreate();
//            }
//        });

        return rowView;
    }
}