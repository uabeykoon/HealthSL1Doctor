package com.example.healthsl1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DoctorsChannaling extends AppCompatActivity {

    String finalDay;
    String[] dateSequence = new String[7];
    String dayOfWeek;
    String selectedDayOfWeek;
    String doctorID;


    ListView doctorChannelListView;

    List<DoctorChannelDetails> doctorChanneling;
    List<MakeChannal> doctorChanneling1;

    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_channaling);

        doctorChanneling = new ArrayList<>();
        doctorChanneling1 = new ArrayList<>();

        doctorChannelListView = (ListView)findViewById(R.id.doctorChannelListView) ;

        get7Days();



        //get the values parsing from before intent

            Intent intent = getIntent();
            dayOfWeek = intent.getStringExtra("position");
            doctorID = intent.getStringExtra("doctorId");

        //Toast.makeText(DoctorsChannaling.this,selectedDayOfWeek,Toast.LENGTH_LONG).show();






        //select the relevant day of week

                    switch (dayOfWeek){
                        case "0":
                            selectedDayOfWeek=dateSequence[0];
                            break;
                        case "1":
                            selectedDayOfWeek=dateSequence[1];
                            break;
                        case "2":
                            selectedDayOfWeek=dateSequence[2];
                            break;
                        case "3":
                            selectedDayOfWeek=dateSequence[3];
                            break;
                        case "4":
                            selectedDayOfWeek=dateSequence[4];
                            break;
                        case "5":
                            selectedDayOfWeek=dateSequence[5];
                            break;
                        case "6":
                            selectedDayOfWeek=dateSequence[6];
                            break;

                    }


        Toast.makeText(DoctorsChannaling.this,doctorID,Toast.LENGTH_LONG).show();

//get channel id related to specific date and doctor


        Query query  = FirebaseDatabase.getInstance().getReference().child("DoctorChannelDetails").child(doctorID).orderByChild("date").equalTo(selectedDayOfWeek);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot chanallingSnapshot : dataSnapshot.getChildren()){
                        DoctorChannelDetails chanalling = chanallingSnapshot.getValue(DoctorChannelDetails.class);

                        if (chanalling.getChannalNumber().equals("1")) {
                            doctorChanneling.add(chanalling);
                        }
                    }

                    Toast.makeText(DoctorsChannaling.this,doctorChanneling.get(0).getChannelId(),Toast.LENGTH_LONG).show();
                    //Toast.makeText(DoctorsChannaling.this,doctorChanneling.get(1).getPatientName(),Toast.LENGTH_LONG).show();

            //get patient details for specific channal

                    databaseReference = FirebaseDatabase.getInstance().getReference("ChannalAdded").child(selectedDayOfWeek).child(doctorChanneling.get(0).getChannelId());
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.exists()) {
                                for (DataSnapshot chanallingSnapshot1 : dataSnapshot.getChildren()) {
                                    MakeChannal chanalling1 = chanallingSnapshot1.getValue(MakeChannal.class);

                                    if (chanalling1.channelNumber.equals("0")) {

                                    } else {
                                        doctorChanneling1.add(chanalling1);
                                    }
                                }

                                DoctorChannalingList adapter = new DoctorChannalingList(DoctorsChannaling.this,doctorChanneling1);
                                doctorChannelListView.setAdapter(adapter);

                            }




                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




                }
                else {
                    Toast.makeText(DoctorsChannaling.this,"No Channeling",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });









        /*databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot chanallingSnapshot : dataSnapshot.getChildren()){
                    DoctorChannelDetails chanalling = chanallingSnapshot.getValue(DoctorChannelDetails.class);
                    doctorChanneling.add(chanalling);
                }
                Toast.makeText(DoctorsChannaling.this,doctorChanneling.get(0).channelId,Toast.LENGTH_LONG).show();



                String a = dataSnapshot.child("patientName").getValue().toString();

                Toast.makeText(DoctorsChannaling.this,a,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        */













        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                chanallingList.clear();

                for (DataSnapshot chanallingSnapshot : dataSnapshot.getChildren()){
                    Chanalling chanalling = chanallingSnapshot.getValue(Chanalling.class);
                    chanallingList.add(chanalling);
                }

                //set value to the list view

                ChanalList adapter = new ChanalList(ChanallingBase.this,chanallingList);
                listViewChanalling.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        */



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
        //Toast.makeText(MyChanneling.this,dateSequence[3],Toast.LENGTH_LONG).show();

    }



}
