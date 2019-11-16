package com.example.healthsl1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FirstFillDetails extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText fName, lName, tpNumber;
    Spinner specification1;
    Button addDetails;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_fill_details);


    //get parsing userID

        Intent intent = getIntent();
        userID = intent.getStringExtra("userId");

       addDetails = (Button)findViewById(R.id.addDetails);

       addDetails.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               addData();
               Toast.makeText(FirstFillDetails.this, userID, Toast.LENGTH_LONG).show();



    //open another activity after filling

               Intent intent1 = new Intent(FirstFillDetails.this,DoctorBase.class);
               intent1.putExtra("doctorId",userID);
               intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               startActivity(intent1);
           }
       });


    }




            public void addData() {
                fName = (EditText) findViewById(R.id.firstName);
                lName = (EditText) findViewById(R.id.lastName);
                specification1 = (Spinner)findViewById(R.id.specification);
                tpNumber = (EditText) findViewById(R.id.phoneNumber);





                        //Toast.makeText(FirstFillDetails.this, userID, Toast.LENGTH_LONG).show();

                        String firstName = fName.getText().toString();
                        String lastName = lName.getText().toString();
                        String specification = specification1.getSelectedItem().toString();
                        String phoneNumber = tpNumber.getText().toString();


                //get database reference

                        databaseReference = FirebaseDatabase.getInstance().getReference("Doctors");
                        Doctors doctors = new Doctors(userID,firstName,lastName,specification,""+phoneNumber);
                        databaseReference.child(userID).setValue(doctors);





            }
        }

