package com.base.ProjectTestAndroid;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReminderActivity extends AppCompatActivity {
    LinearLayout layout;
    public FirebaseDatabase dbMain = FirebaseDatabase.getInstance();
    private  String regno = "";
    public DatabaseReference dEvents = dbMain.getReference("Events");
    public DatabaseReference dTasks = dbMain.getReference("Tasks");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        WindowInsetsController insetsController = decorView.getWindowInsetsController();

        if (insetsController != null) {
            insetsController.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars() | WindowInsets.Type.systemBars());
            insetsController.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }

        setContentView(R.layout.activity_reminder);
        layout = findViewById(R.id.cardContainer);
        ImageView BackButton = findViewById(R.id.imageView20);

        getmailandregno GMA = new getmailandregno();
        getmailandregno.Callback Callback = new getmailandregno.Callback(){
            @Override
            public void onDataReceived() {
                regno = GMA.getRegisternumber();
                display_all(regno);
            }
        };
        GMA.getValues(Callback);


        BackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ReminderActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void display_all(String Reg_No){
        dEvents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot EventSnapShot : dataSnapshot.getChildren()) {
                    String EventID = EventSnapShot.getKey();
                    Event EventOBJ = EventSnapShot.getValue(Event.class);
                    String dateEvent = EventOBJ.getDate();
                    if(EventOBJ.getRegisteredUsers().contains(Reg_No)) {
                        if (compare_Dates(dateEvent) == 0) {
                            View view = getLayoutInflater().inflate(R.layout.cardremainder, null);
                            TextView Name = view.findViewById(R.id.textView51);
                            TextView Type = view.findViewById(R.id.textView52);
                            TextView Desc = view.findViewById(R.id.textView53);
                            TextView Venue = view.findViewById(R.id.textView49);
                            TextView Event_date = view.findViewById(R.id.textView54);
                            TextView Event_Time = view.findViewById(R.id.textView55);

                            Name.setText(EventOBJ.getName());
                            Type.setText("EVENT");
                            Type.setTextColor(Color.parseColor("#A90084"));
                            try{
                                Desc.setText(EventOBJ.getDescription().substring(0,30)+" ...");
                            }catch (IndexOutOfBoundsException e){
                                Desc.setText(EventOBJ.getDescription());
                            }
                            Venue.setText("Venue: " + EventOBJ.getVenue());
                            Event_date.setText("Date: " + EventOBJ.getDate());
                            Event_Time.setText("Time: " + EventOBJ.getStartTime() + " - " + EventOBJ.getEndTime());
                            layout.addView(view);
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Error In Database: "+ error.toException());
            }
        });

        dTasks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot TaskSnapShot : dataSnapshot.getChildren()) {
                    String TaskID = TaskSnapShot.getKey();
                    Task TaskOBJ = TaskSnapShot.getValue(Task.class);
                    String dateTask = TaskOBJ.getDate();
                    if(TaskOBJ.getRegisteredUsers().contains(Reg_No)) {
                        if (compare_Dates(dateTask) == 0) {
                            View view = getLayoutInflater().inflate(R.layout.cardremainder, null);
                            TextView Name = view.findViewById(R.id.textView51);
                            TextView Type = view.findViewById(R.id.textView52);
                            TextView Desc = view.findViewById(R.id.textView53);
                            TextView Venue = view.findViewById(R.id.textView49);
                            TextView Task_date = view.findViewById(R.id.textView54);
                            TextView Task_Time = view.findViewById(R.id.textView55);

                            Name.setText(TaskOBJ.getName());
                            Type.setText("TASK");
                            Type.setTextColor(Color.parseColor("#CBBA00"));
                            try{
                                Desc.setText(TaskOBJ.getDescription().substring(0,30)+" ...");
                            }catch (IndexOutOfBoundsException e){
                                Desc.setText(TaskOBJ.getDescription());
                            }
                            Venue.setText("Venue: " + TaskOBJ.getVenue());
                            Task_date.setText("Date: " + TaskOBJ.getDate());
                            Task_Time.setText("Time: " + TaskOBJ.getStartTime() + " - " + TaskOBJ.getEndTime());
                            layout.addView(view);
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Error In Database: "+ error.toException());
            }
        });
    }

    private int compare_Dates(String EventDate){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String todayDate = dateFormat.format(calendar.getTime()).toString();
        int result = 0;
        try {
            Date TodayDate = dateFormat.parse(todayDate);
            Date dateEvent = dateFormat.parse(EventDate);
            result =TodayDate.compareTo(dateEvent);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return result;
    }

}
