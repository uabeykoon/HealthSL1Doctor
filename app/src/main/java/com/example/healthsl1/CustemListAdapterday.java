package com.example.healthsl1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class CustemListAdapterday extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] itemname;


    public CustemListAdapterday(Activity context, String[] itemname) {
        super(context, R.layout.daysample,itemname);
// TODO Auto-generated constructor stub
        this.context=context;
        this.itemname=itemname;

    }

    @Override
    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.daysample, null,true);


        TextView txtTitle1 = (TextView) rowView.findViewById(R.id.day);





        txtTitle1.setText("  "+itemname[position]);

        return rowView;






    };

}