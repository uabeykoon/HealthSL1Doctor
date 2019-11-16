package com.example.healthsl1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Home extends AppCompatActivity {


    private DrawerLayout hDrawerlayout;
    private ActionBarDrawerToggle hToggle;
    TimePickerDialog timePickerDialog;
    DatabaseReference databaseReference;

    DatePickerDialog.OnDateSetListener mDateSetListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        //Navigation drawer button


                    hDrawerlayout = (DrawerLayout)findViewById(R.id.drawer);
                    hToggle= new ActionBarDrawerToggle(this,hDrawerlayout,R.string.open,R.string.close);
                    hDrawerlayout.addDrawerListener(hToggle);
                    hToggle.syncState();
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);


         //get database reference














    }

    //Navigation on and off method

                    @Override
                    public boolean onOptionsItemSelected(MenuItem item) {

                        if (hToggle.onOptionsItemSelected(item)){
                            return true;
                        }
                        return super.onOptionsItemSelected(item);
                    }


}
