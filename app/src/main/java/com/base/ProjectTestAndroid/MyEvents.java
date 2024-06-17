package com.base.ProjectTestAndroid;


import android.content.*;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.os.Build;
import android.app.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import java.util.Map;

public class MyEvents extends AppCompatActivity {

    LinearLayout layout;
    Button Button2;
    Button Button3 ;
    Button Button7 ;

    public FirebaseDatabase dbMain = FirebaseDatabase.getInstance();
    public DatabaseReference dProfile = dbMain.getReference("Profile");
    public DatabaseReference dOTP = dbMain.getReference("OTP");
    private  String regno = "";
    public DatabaseReference dEvents = dbMain.getReference("Events");


    public void upButtonenable(Button b){

        Button2.setBackgroundResource(R.drawable.button_filterdesel);
        Button3.setBackgroundResource(R.drawable.button_filterdesel);
        Button7.setBackgroundResource(R.drawable.button_filterdesel);

        b.setBackgroundResource(R.drawable.button_filtersel);
        filter_my_events(b.getText().toString());
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        WindowInsetsController insetsController = decorView.getWindowInsetsController();

        if (insetsController != null) {
            insetsController.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars() | WindowInsets.Type.systemBars());
            insetsController.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }

        setContentView(R.layout.activity_myevents);


        layout = findViewById(R.id.cardContainer);
        ImageView Back = findViewById(R.id.imageView40);
        Button2 =findViewById(R.id.button2);
        Button3 =findViewById(R.id.button3);
        Button7 =findViewById(R.id.button7);

        getmailandregno GMA = new getmailandregno();
        getmailandregno.Callback Callback = new getmailandregno.Callback(){
            @Override
            public void onDataReceived() {
                regno = GMA.getRegisternumber();
                filter_my_events("all");
            }
        };
        GMA.getValues(Callback);

        //TODO: OTP Buttons Auto Focus

        Back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyEvents.this, ActivityProfile.class);
                startActivity(intent);
            }
        });

        Button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                upButtonenable(Button2);
            }
        });

        Button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                upButtonenable(Button3);
            }
        });

        Button7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                upButtonenable(Button7);
            }
        });

    }


    private void filter_my_events(String type){
        dEvents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                layout.removeAllViews();
                for (DataSnapshot EventSnapShot : dataSnapshot.getChildren()) {
                    String EventID = EventSnapShot.getKey();
                    Event EventOBJ = EventSnapShot.getValue(Event.class);
                    String Eventdate = EventOBJ.getDate();
                    if(EventOBJ.getRegisteredUsers().contains(regno)) {
                        if(type.equalsIgnoreCase("Past")){
                            if(compare_Dates(Eventdate)>0){
                                View view = getLayoutInflater().inflate(R.layout.cardevent,null);
                                TextView EventName = view.findViewById(R.id.textView51);
                                TextView ClubName = view.findViewById(R.id.textView52);
                                TextView Desc = view.findViewById(R.id.textView53);
                                TextView Venue = view.findViewById(R.id.textView49);
                                TextView Event_date = view.findViewById(R.id.textView54);
                                TextView Event_Time = view.findViewById(R.id.textView55);
                                TextView Event_ID = view.findViewById(R.id.textView56);
                                ImageView ButtonReg = view.findViewById(R.id.imageView31);
                                ImageView ButtonRegistered = view.findViewById(R.id.imageView32);
                                ImageView ButtonFilled = view.findViewById(R.id.imageView26);

                                Event_ID.setText("Event ID: "+EventID);
                                EventName.setText(EventOBJ.getName());
                                ClubName.setText(EventOBJ.getCC());
                                Desc.setText(EventOBJ.getDescription());
                                Venue.setText("Venue: "+EventOBJ.getVenue());
                                Event_date.setText("Date: " +EventOBJ.getDate());
                                Event_Time.setText("Time: "+EventOBJ.getStartTime()+" - "+EventOBJ.getEndTime());
                                display_eventcard_button(ButtonReg,ButtonFilled,ButtonRegistered);
                                layout.addView(view);
                            }
                        }else if(type.equalsIgnoreCase("Upcoming")){
                            if(compare_Dates(Eventdate)<=0){
                                View view = getLayoutInflater().inflate(R.layout.cardevent,null);
                                TextView EventName = view.findViewById(R.id.textView51);
                                TextView ClubName = view.findViewById(R.id.textView52);
                                TextView Desc = view.findViewById(R.id.textView53);
                                TextView Venue = view.findViewById(R.id.textView49);
                                TextView Event_date = view.findViewById(R.id.textView54);
                                TextView Event_Time = view.findViewById(R.id.textView55);
                                TextView Event_ID = view.findViewById(R.id.textView56);
                                ImageView ButtonReg = view.findViewById(R.id.imageView31);
                                ImageView ButtonRegistered = view.findViewById(R.id.imageView32);
                                ImageView ButtonFilled = view.findViewById(R.id.imageView26);

                                Event_ID.setText("Event ID: "+EventID);
                                EventName.setText(EventOBJ.getName());
                                ClubName.setText(EventOBJ.getCC());
                                Desc.setText(EventOBJ.getDescription());
                                Venue.setText("Venue: "+EventOBJ.getVenue());
                                Event_date.setText("Date: " +EventOBJ.getDate());
                                Event_Time.setText("Time: "+EventOBJ.getStartTime()+" - "+EventOBJ.getEndTime());
                                display_eventcard_button(ButtonReg,ButtonFilled,ButtonRegistered);
                                layout.addView(view);
                            }
                        }else{
                            View view = getLayoutInflater().inflate(R.layout.cardevent,null);
                            TextView EventName = view.findViewById(R.id.textView51);
                            TextView ClubName = view.findViewById(R.id.textView52);
                            TextView Desc = view.findViewById(R.id.textView53);
                            TextView Venue = view.findViewById(R.id.textView49);
                            TextView Event_date = view.findViewById(R.id.textView54);
                            TextView Event_Time = view.findViewById(R.id.textView55);
                            TextView Event_ID = view.findViewById(R.id.textView56);
                            ImageView ButtonReg = view.findViewById(R.id.imageView31);
                            ImageView ButtonRegistered = view.findViewById(R.id.imageView32);
                            ImageView ButtonFilled = view.findViewById(R.id.imageView26);

                            Event_ID.setText("Event ID: "+EventID);
                            EventName.setText(EventOBJ.getName());
                            ClubName.setText(EventOBJ.getCC());
                            Desc.setText(EventOBJ.getDescription());
                            Venue.setText("Venue: "+EventOBJ.getVenue());
                            Event_date.setText("Date: " +EventOBJ.getDate());
                            Event_Time.setText("Time: "+EventOBJ.getStartTime()+" - "+EventOBJ.getEndTime());
                            display_eventcard_button(ButtonReg,ButtonFilled,ButtonRegistered);
                            layout.addView(view);
                        }
                    }else{
                        System.out.println("No Registered Events Found");
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

    private void display_eventcard_button(ImageView a,ImageView b,ImageView c){
        a.setVisibility(View.INVISIBLE);
        b.setVisibility(View.INVISIBLE);
        c.setVisibility(View.INVISIBLE);
    }

}
