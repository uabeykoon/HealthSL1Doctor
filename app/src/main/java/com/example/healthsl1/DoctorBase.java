package com.example.healthsl1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DoctorBase extends AppCompatActivity {

    String[] dateSequence = new String[7];
    String finalDay;
    String doctorID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_base);


        Intent intent = getIntent();
        doctorID=intent.getStringExtra("userId");

        Toast.makeText(DoctorBase.this,doctorID,Toast.LENGTH_LONG).show();

        ListView doctorBaseListView = (ListView)findViewById(R.id.doctorBaseList) ;

        get7Days();

        CustemListAdapterday custemListAdapterday = new CustemListAdapterday(DoctorBase.this,dateSequence);
        doctorBaseListView.setAdapter(custemListAdapterday);


        doctorBaseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent1 = new Intent(DoctorBase.this,DoctorsChannaling.class);
                intent1.putExtra("doctorId",doctorID);
                intent1.putExtra("position",""+position);
                startActivity(intent1);

            }
        });



    }


    public void get7Days(){


        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat msimpleDateFormat=new SimpleDateFormat("d-M-yyyy");

        dateSequence[0]=msimpleDateFormat.format(calendar.getTime());

        Date dt2 = null;
        try {
            dt2 = msimpleDateFormat.parse(msimpleDateFormat.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat format3 = new SimpleDateFormat("EEEE");
        finalDay = format3.format(dt2);
        dateSequence[0] = msimpleDateFormat.format(calendar.getTime())+","+finalDay;

        //get 7 date to future


        for (int i=1;i<7;i++) {
            calendar.add(Calendar.DATE, 1);
            //SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
            Date dt1 = null;
            try {
                dt1 = msimpleDateFormat.parse(msimpleDateFormat.format(calendar.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            DateFormat format2 = new SimpleDateFormat("EEEE");
            finalDay = format2.format(dt1);

            dateSequence[i] = msimpleDateFormat.format(calendar.getTime())+","+finalDay;
        }
        //Toast.makeText(DoctorBase.this,dateSequence[6],Toast.LENGTH_LONG).show();

    }
}
