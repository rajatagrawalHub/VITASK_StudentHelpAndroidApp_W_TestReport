package com.base.ProjectTestAndroid;


import android.content.*;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.os.Build;
import android.app.*;
import androidx.core.app.*;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dTasks = database.getReference("Tasks");

    EditText Namefield;
    EditText DescField;
    EditText VenueField;
    EditText DateField;
    EditText StartTimeField;
    EditText EndTimeField;

    String regno = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        WindowInsetsController insetsController = decorView.getWindowInsetsController();

        if (insetsController != null) {
            insetsController.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars() | WindowInsets.Type.systemBars());
            insetsController.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }

        setContentView(R.layout.activity_addtask);

        ImageView AccountButton = findViewById(R.id.imageView16);
        ImageView CCButton = findViewById(R.id.imageView17);
        ImageView EventButton = findViewById(R.id.imageView19);
        ImageView AddButton = findViewById(R.id.imageView18);
        ImageView HomeButton = findViewById(R.id.imageView15);
        Button addTask = findViewById(R.id.buttonGetOtp3);
        Namefield = findViewById(R.id.editTextText2);
        DescField = findViewById(R.id.editTextText3);
        VenueField = findViewById(R.id.editTextText4);
        DateField = findViewById(R.id.editTextText5);
        StartTimeField = findViewById(R.id.editTextText6);
        EndTimeField = findViewById(R.id.editTextText11);

        getmailandregno GMA = new getmailandregno();

        getmailandregno.Callback Callback = new getmailandregno.Callback(){
            @Override
            public void onDataReceived() {
                regno = GMA.getRegisternumber();
            }
        };

        GMA.getValues(Callback);

        AccountButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTaskActivity.this, ActivityProfile.class);
                startActivity(intent);
            }
        });

        CCButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTaskActivity.this, CCSelectionmenu.class);
                startActivity(intent);
            }
        });

        EventButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTaskActivity.this, EventActivity.class);
                startActivity(intent);
            }
        });

        AddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTaskActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTaskActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        addTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Task task = new Task();
                task.setName(Namefield.getText().toString());
                task.setDescription(DescField.getText().toString());
                task.setVenue(VenueField.getText().toString());
                task.setRegisteredUsers(regno);
                if (verify_Date(DateField.getText().toString(), DateField) && verify_Time(StartTimeField.getText().toString(), StartTimeField) && verify_Time(EndTimeField.getText().toString(), EndTimeField) && verify_description(DescField.getText().toString())) {
                    task.setDate(DateField.getText().toString());
                    task.setStartTime(StartTimeField.getText().toString());
                    task.setEndTime(EndTimeField.getText().toString());
                    dTasks.child(Namefield.getText().toString().substring(0,4)+regno.substring(5)+DateField.getText().toString().substring(0,2)+DateField.getText().toString().substring(3,5)+DateField.getText().toString().substring(6,8)).setValue(task);
                    showAlertDialog("Successfull","Task Added Succesfully");
                    set_all_null();
                }
            }
        });



    }
    
    private void set_all_null(){
         Namefield.setText(null);
         DescField.setText(null) ;
         VenueField.setText(null);
         DateField.setText(null) ;
         StartTimeField.setText(null) ;
         EndTimeField.setText(null);
    }

    private boolean verify_description(String Desc_Length){
        if(Desc_Length.length()>30){
            showAlertDialog("Invalid Descripton","Description Should be 40 letters");
            return false;
        }else{
            return true;
        }
    }

    private boolean verify_Date(String dateEntered, EditText ED){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date TodayDate = dateFormat.parse(dateEntered);
            return true;
        }catch(Exception e){
            ED.setText(null);
            showAlertDialog("Invalid Date","Please Enter Date As 01-01-1999");
            return false;
        }
    }
    private boolean verify_Time(String timeEntered,EditText ED){
        try{
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date TodayTime = timeFormat.parse(timeEntered);
            return true;
        }catch(Exception e){
            ED.setText(null);
            showAlertDialog("Invalid Time","Please Enter Time As 13:00");
            return false;
        }
    }
    private void showAlertDialog(String Title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(Title);
        builder.setMessage(Message);


        // Positive button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        if (dialog.getWindow() != null) {
            dialog.getWindow().getDecorView().setBackgroundResource(R.drawable.dialogbox);
        }

    }

}
