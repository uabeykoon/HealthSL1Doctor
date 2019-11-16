package com.example.healthsl1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AdminChanaling extends AppCompatActivity {
    DatabaseReference databaseReference;
    String userID;
    String date[]=new String[7];
    String aaa[] = {"abc","def","ijk"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_chanaling);





        //get todays date front

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat msimpleDateFormat=new SimpleDateFormat("EEEE");
        date[0]=msimpleDateFormat.format(calendar.getTime());
        for (int i=1;i<7;i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            date[i] = msimpleDateFormat.format(calendar.getTime());
        }


        final Intent intent = getIntent();
        userID = intent.getStringExtra("userID");

        CustemListAdapterday adepter = new CustemListAdapterday(AdminChanaling.this,date);
        ListView listmain= (ListView)findViewById(R.id.daylist) ;
        listmain.setAdapter(adepter);

    //click in the item of list view

        listmain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(AdminChanaling.this,position,Toast.LENGTH_LONG).show();
                String position1 =Integer.toString(position);

                Intent intent1 = new Intent(AdminChanaling.this,ChanallingBase.class);
                intent1.putExtra("day",position1);
                startActivity(intent1);




            }
        });


    }


    public void chanel(){
        //if (userID.equals(""))


    }
}
