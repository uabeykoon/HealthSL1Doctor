package com.example.healthsl1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DoctorChannalingList extends ArrayAdapter<MakeChannal> {

    private Activity contex;
    private List<MakeChannal> doctorChanallingList;


    public DoctorChannalingList(Activity contex,List<MakeChannal> doctorChanallingLists){
        super(contex,R.layout.doctor_channaling_sample,doctorChanallingLists);
        this.contex=contex;
        this.doctorChanallingList = doctorChanallingLists;
    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=contex.getLayoutInflater();

        View listView = inflater.inflate(R.layout.doctor_channaling_sample,null,true);
        TextView textView1 = (TextView)listView.findViewById(R.id.startTimedc);
        TextView textView2 = (TextView)listView.findViewById(R.id.endTimedc);


        MakeChannal doctorChannelDetails = doctorChanallingList.get(position);



        textView1.setText("#"+doctorChannelDetails.getChannelNumber());
        textView2.setText(doctorChannelDetails.getPatientName());




        return listView;
    }




}
