package com.example.healthsl1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChanallingBase extends AppCompatActivity {

    String[] chanallingcode;
    String[] doctorName;
    FloatingActionButton ftb;
    String dayOfWeek;
    String selectedDayOfWeek;
    DatabaseReference databaseReference;
    String[] startTime;
    String[] date = new String[7];

    ListView listViewChanalling;

    List<Chanalling> chanallingList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chanalling_base);

        listViewChanalling = (ListView)findViewById(R.id.kkkk);


        chanallingList = new ArrayList<>();
        ListView chanallinglist1 = (ListView)findViewById(R.id.kkkk);

        //getting number that parsing from before page relavent to day

                    final Intent intent= getIntent();
                    dayOfWeek = intent.getStringExtra("day");

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat msimpleDateFormat=new SimpleDateFormat("EEEE");
        date[0]=msimpleDateFormat.format(calendar.getTime());
        for (int i=1;i<7;i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            date[i] = msimpleDateFormat.format(calendar.getTime());
        }

         //select the relevant day of week

                    switch (dayOfWeek){
                        case "0":
                            selectedDayOfWeek=date[0];
                            break;
                        case "1":
                            selectedDayOfWeek=date[1];
                            break;
                        case "2":
                            selectedDayOfWeek=date[2];
                            break;
                        case "3":
                            selectedDayOfWeek=date[3];
                            break;
                        case "4":
                            selectedDayOfWeek=date[4];
                            break;
                        case "5":
                            selectedDayOfWeek=date[5];
                            break;
                        case "6":
                            selectedDayOfWeek=date[6];
                            break;

                    }



    //creating database reference

        databaseReference = FirebaseDatabase.getInstance().getReference("Channaling").child(selectedDayOfWeek);





    //casting floating button

        ftb = (FloatingActionButton)findViewById(R.id.addChanalButton);


    //floating button clicked

        ftb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChanallingBase.this,"clicked",Toast.LENGTH_LONG).show();

         //open new activity to add chanal
                Intent intent1 = new Intent(ChanallingBase.this,AddChanal.class);
                intent1.putExtra("day",dayOfWeek);
                startActivity(intent1);






            }
        });




    //click on one item in the list

        listViewChanalling.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Chanalling chanalling = chanallingList.get(position);
                String chanallingId = chanalling.getChanallingId();


                Intent intent1 = new Intent(ChanallingBase.this,EditChannel.class);
                intent1.putExtra("chanallingId",chanallingId);
                intent1.putExtra("selectedDayOfWeek",selectedDayOfWeek);
                intent1.putExtra("day",position);
                startActivity(intent1);


                Toast.makeText(ChanallingBase.this,chanallingId,Toast.LENGTH_LONG).show();



            }
        });










    }






    @Override
    protected void onStart() {
        super.onStart();

    //getting value from database

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                chanallingList.clear();
                if (dataSnapshot.exists()) {

                    for (DataSnapshot chanallingSnapshot : dataSnapshot.getChildren()) {
                        Chanalling chanalling = chanallingSnapshot.getValue(Chanalling.class);
                        chanallingList.add(chanalling);
                    }

                    //set value to the list view

                    ChanalList adapter = new ChanalList(ChanallingBase.this, chanallingList);
                    listViewChanalling.setAdapter(adapter);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}

