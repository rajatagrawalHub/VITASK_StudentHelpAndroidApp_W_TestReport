package com.base.ProjectTestAndroid;


import android.content.*;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.os.Build;
import android.app.*;

import androidx.annotation.NonNull;
import androidx.core.app.*;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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


public class HomeActivity extends AppCompatActivity {
    public FirebaseDatabase dbMain = FirebaseDatabase.getInstance();
    public DatabaseReference dEvents = dbMain.getReference("Events");
    public DatabaseReference dTasks = dbMain.getReference("Tasks");
    LinearLayout layouttask;
    LinearLayout layoutevent;
    String mailEntered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        WindowInsetsController insetsController = decorView.getWindowInsetsController();

        if (insetsController != null) {
            insetsController.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars() | WindowInsets.Type.systemBars());
            insetsController.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }

        setContentView(R.layout.activity_home);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Trial101", "VIT_notification_Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Trial101")
                .setSmallIcon(R.drawable.asklogo)
                .setContentTitle("Success Message - 2 ")
                .setContentText("Thank you for assisting in Successful testing of Stage 2")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(resultPendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

        ImageView AccountButton = findViewById(R.id.imageView16);
        ImageView CCButton = findViewById(R.id.imageView17);
        ImageView EventButton = findViewById(R.id.imageView19);
        ImageView AddButton = findViewById(R.id.imageView18);
        ImageView HomeButton = findViewById(R.id.imageView15);
        ImageView RemainderButton = findViewById(R.id.imageView9);
        layouttask = findViewById(R.id.linearlayouttask);
        layoutevent = findViewById(R.id.linearlayoutevent);
        Intent IntentOTP = getIntent();
        mailEntered = IntentOTP.getStringExtra("Email");

        getmailandregno GMA = new getmailandregno();

        getmailandregno.Callback Callback = new getmailandregno.Callback(){
            @Override
            public void onDataReceived() {
                String Regno = GMA.getRegisternumber();
                get_today_events(Regno);
                get_today_tasks(Regno);
            }
        };

        GMA.getValues(Callback);


        AccountButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, ActivityProfile.class);
                intent.putExtra("Email",mailEntered);
                startActivity(intent);
            }
        });

        CCButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CCSelectionmenu.class);
                startActivity(intent);
            }
        });

        EventButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EventActivity.class);
                startActivity(intent);
            }
        });

        AddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        RemainderButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomeActivity.this, ReminderActivity.class);
                startActivity(intent);
            }
        });

    }

    private void get_today_events(String Reg_No){
        dEvents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                layoutevent.removeAllViews();
                for (DataSnapshot EventSnapShot : dataSnapshot.getChildren()) {
                    String EventID = EventSnapShot.getKey();
                    Event EventOBJ = EventSnapShot.getValue(Event.class);
                    String Eventdate = EventOBJ.getDate();
                    if(EventOBJ.getRegisteredUsers().contains(Reg_No)) {
                        if(compare_Dates(Eventdate)==0){
                            TextView EventNameView = new TextView(HomeActivity.this);
                            EventNameView.setText(EventOBJ.getName());
                            EventNameView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            EventNameView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
                            EventNameView.setPadding(0,2,0,2);
                            layoutevent.addView(EventNameView);
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
    private void get_today_tasks(String Reg_No){
        dTasks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                layouttask.removeAllViews();
                for (DataSnapshot TaskSnapShot : dataSnapshot.getChildren()) {
                    String TaskID = TaskSnapShot.getKey();
                    Task TaskOBJ = TaskSnapShot.getValue(Task.class);
                    String TaskDate = TaskOBJ.getDate();
                    if(TaskOBJ.getRegisteredUsers().contains(Reg_No)) {
                        if(compare_Dates(TaskDate)==0){
                            TextView TaskNameView = new TextView(HomeActivity.this);
                            TaskNameView.setText(TaskOBJ.getName());
                            TaskNameView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            TaskNameView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
                            TaskNameView.setPadding(0,2,0,2);
                            layouttask.addView(TaskNameView);
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
