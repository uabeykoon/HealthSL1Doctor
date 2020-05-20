package com.example.healthsl1;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
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

public class AddChanal extends AppCompatActivity {

    private static int selectedStartHour;
    private static int selectedStartMinute;
    private static int selectedEndHour;
    private static int selectedEndMinute;
    String name;
    String doctorID;

    String[] date = new String[7];
    String day;
    String selectedDayOfWeek;
    String maxPatients;
    String specification;
    List<Doctors> doctorNames;
    int doctorCount;
    String[] doctorsNameArray = new String[doctorCount];
    Spinner doctorsNameSpinner;
    String doctorName;


    EditText maxp;


    DatabaseReference databaseReference;
    DatabaseReference databaseReferenceDoctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chanal);


        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat msimpleDateFormat=new SimpleDateFormat("EEEE");
        date[0]=msimpleDateFormat.format(calendar.getTime());
        for (int i=1;i<7;i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            date[i] = msimpleDateFormat.format(calendar.getTime());
        }

        doctorNames = new ArrayList<Doctors>();

        Intent intent= getIntent();
        day = intent.getStringExtra("day");

//select the relevant day of week

        switch (day){
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





        final EditText startTime = (EditText) findViewById(R.id.startTime);
        final EditText endtTime = (EditText) findViewById(R.id.endTime);
        Button addChanal = (Button)findViewById(R.id.add1Chanal);
        maxp =(EditText)findViewById(R.id.maxPatient);




    //start time selecter

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                final int hour = cal.get(Calendar.HOUR_OF_DAY);
                final int minute1 = cal.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddChanal.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            //add selected date to class variable

                        AddChanal.selectedStartHour=hourOfDay;
                        AddChanal.selectedStartMinute=minute;


            //set selected date to edit text field

                        startTime.setText(hourOfDay +"."+ minute);

                    }
                },hour,minute1,false);
                timePickerDialog.show();




            }
        });



    //End time selector


        endtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                final int hour = cal.get(Calendar.HOUR_OF_DAY);
                final int minute1 = cal.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddChanal.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        //add selected date to class variable

                        AddChanal.selectedEndHour=hourOfDay;
                        AddChanal.selectedEndMinute=minute;


                        //set selected date to edit text field

                        endtTime.setText(hourOfDay +"."+ minute);

                    }
                },hour,minute1,false);
                timePickerDialog.show();




            }
        });





        //retrieve doctors name to show on spinner


            databaseReferenceDoctors = FirebaseDatabase.getInstance().getReference("Doctors");

            databaseReferenceDoctors.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            //get doctors count

                    doctorCount = (int)dataSnapshot.getChildrenCount();
                    doctorsNameArray = new String[doctorCount];


            //getting all the element in doctors node
//                    doctorNames.add

                    for (DataSnapshot doctorsSnapshot : dataSnapshot.getChildren()){

            //get doctors reference

                        Doctors doctors = doctorsSnapshot.getValue(Doctors.class);
//                        String d = doctors.getFirstName();

            //add that data to list

                        doctorNames.add(doctors);
                    }



                    /*Doctors doctors = doctorNames.get(0);
                    String a = doctors.getFirstName();
                    doctorsNameArray[0] = doctors.getFirstName();*/

            //add details to arrays

                /* for (int i=0;i<doctorCount;i++) {
                    Doctors doctors = doctorNames.get(i);
                    doctorsNameArray[i] = doctors.getFirstName();
                }*/

                    //Toast.makeText(AddChanal.this,doctorsNameArray[1],Toast.LENGTH_LONG).show();




                    //Toast.makeText(AddChanal.this,doctorCount,Toast.LENGTH_LONG).show();

                    //String[] array = doctorNames.toArray(new String[0]);

                    spinner();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




//click on add button after filling

        addChanal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // name = doctorsNameSpinner.getSelectedItem().toString();



                maxPatients = maxp.getText().toString();


                //get database reference to send data

                databaseReference = FirebaseDatabase.getInstance().getReference("Channaling").child(selectedDayOfWeek);
                String id = databaseReference.push().getKey();

                Chanalling chanalling = new Chanalling(id,selectedDayOfWeek,doctorID,doctorName,selectedStartHour+"."+selectedStartMinute,selectedEndHour+"."+selectedEndMinute,maxPatients);
                //Chanalling testing = new Chanalling("1","2","3","4","5"+"."+"6","7"+"."+"8","9");
                databaseReference.child(id).setValue(chanalling);
                //mapping data before send
                        /*Map sendData = new HashMap();

                        sendData.put("Start Time ", selectedStartHour+"."+selectedStartMinute);
                        sendData.put("End Time", selectedEndHour+"."+selectedEndMinute);
                        sendData.put("Max Patients", maxPatients);
                        */

                //send values to database

                //databaseReference.child(id).setValue(sendData);
                finish();

                Intent intent1 = new Intent(AddChanal.this,ChanallingBase.class);
                intent1.putExtra("day",day);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);


            }
        });













    }


    public void spinner(){
        // spinner


        doctorsNameSpinner = (Spinner)findViewById(R.id.doctorSpinner);
        ArrayAdapter<Doctors> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,doctorNames);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        doctorsNameSpinner.setAdapter(adapter);

        //on spinner selected

        doctorsNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(AddChanal.this,"hhhhh",Toast.LENGTH_LONG).show();


                Doctors doctors = (Doctors)parent.getSelectedItem();
                displayDoctorsdata(doctors);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void getSelectedDoctors(View v){
        Doctors doctors = (Doctors)doctorsNameSpinner.getSelectedItem();
        displayDoctorsdata(doctors);

    }

    private void displayDoctorsdata(Doctors doctors){
        name = doctors.getFirstName().toString();
        doctorID = doctors.getDoctorID().toString();
        specification=doctors.getSpecification();

        doctorName = doctors.getFirstName().toString() +" "+ doctors.getLastName().toString();

        Toast.makeText(AddChanal.this,name,Toast.LENGTH_LONG).show();
    }

}




