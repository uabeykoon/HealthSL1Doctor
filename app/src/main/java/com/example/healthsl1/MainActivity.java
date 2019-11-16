package com.example.healthsl1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    final int RequestPermissioncod = 0;
    FirebaseAuth mAuth;
    EditText email,password;
    DatabaseReference databaseReference;


    String days[]={"Sunday","Monday"};

    String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CustemListAdapterday adepter = new CustemListAdapterday(MainActivity.this,days);
        //ListView listmain= (ListView)findViewById(R.id.listmain) ;
        //listmain.setAdapter(adepter);


        //EnableRuntimePermission();
        mAuth = FirebaseAuth.getInstance();


            Button login = (Button)findViewById(R.id.login);
            TextView register = (TextView)findViewById(R.id.register);





            //click on login
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userLogin();
                }
            });




            //click on register
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    register();

                }
            });












    }

    //login
            public void userLogin(){


                email=(EditText)findViewById(R.id.loginemail);
                password=(EditText)findViewById(R.id.loginpassword);


                String email1 = email.getText().toString().trim();
                String password1 = password.getText().toString().trim();


                //check is the email field empty

                if (email1.isEmpty()){
                    email.setError("Email is required");
                    email.requestFocus();
                    return;
                }
                //check is the password field empty

                if (password1.isEmpty()){
                    password.setError("Password is required");
                    password.requestFocus();
                    return;
                }
                //check email availability

                if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
                    email.setError("Enter a valid Email address");
                    email.requestFocus();
                    return;
                }

                //check password length

                if (password1.length()<6){
                    password.setError("Minimum length is 6");
                    password.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                 //if email matches password

                       if (task.isSuccessful()) {


                //check if the user is available or not

                //get user id to pass it
                            userID = mAuth.getCurrentUser().getUid();









                            Toast.makeText(MainActivity.this,userID, Toast.LENGTH_LONG).show();
                            String ad = "nLeRlww7i0Y6vDDZM7GOho7NcLx1";


                //choose is the user an admin

                           if (userID.equals(ad)){
                                Intent intent2 = new Intent(MainActivity.this,AdminChanaling.class);
                                intent2.putExtra("userID",userID);
                                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent2);}

                //if a user is a patient
                           else {



                               final DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference("Doctors").child(userID);
                               databaseReference3.addValueEventListener(new ValueEventListener() {
                                   @Override
                                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                       if (dataSnapshot.exists()){

                                           Intent intent3 = new Intent(MainActivity.this,DoctorBase.class);
                                           intent3.putExtra("userId",userID);
                                           intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                           startActivity(intent3);
                                       }

                                       else {

                            //Check is this a patient or a Doctor

                                           DatabaseReference databaseReference4 = FirebaseDatabase.getInstance().getReference("Patients").child(userID);
                                           databaseReference4.addValueEventListener(new ValueEventListener() {
                                               @Override
                                               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                   if (dataSnapshot.exists()){

                                                       Toast.makeText(MainActivity.this,"Please use the Patient version app of HealthSL",Toast.LENGTH_LONG).show();

                                                   }

                                                   else {

                                                       Intent intent4 = new Intent(MainActivity.this, FirstFillDetails.class);
                                                       intent4.putExtra("userId", userID);
                                                       intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                       startActivity(intent4);

                                                   }

                                               }

                                               @Override
                                               public void onCancelled(@NonNull DatabaseError databaseError) {

                                               }
                                           });





                                       }

                                   }

                                   @Override
                                   public void onCancelled(@NonNull DatabaseError databaseError) {

                                   }
                               });






                           }








                       }
                // if there is any error
                       else {
                           Toast.makeText(MainActivity.this, task.getException().getMessage().toString(), Toast.LENGTH_LONG).show();
                       }


                    }
                });



            }

    //register saving data
            public void register(){
                finish();
                Intent intent = new Intent(MainActivity.this,Registration.class);
                startActivity(intent);
            }
    //check wether user is logged in or not

//            @Override
//            protected void onStart() {
//                super.onStart();
//                if (mAuth.getCurrentUser()!=null){
//                    finish();
//                    Intent intent = new Intent(MainActivity.this,Home.class);
//                    startActivity(intent);
//                }
//            }

    //requesting permission
            public void EnableRuntimePermission() {
                String[] permission = {Manifest.permission.INTERNET};
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.INTERNET)) {
                    Toast.makeText(MainActivity.this, "Internet permission allows granted", Toast.LENGTH_LONG).show();
                } else {

                    ActivityCompat.requestPermissions(MainActivity.this, permission, RequestPermissioncod);
                }
            }


}
