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
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class EventActivity extends AppCompatActivity {

    LinearLayout layout;
    ImageView FilterBg ;
    TextView  FilterT1;
    TextView  FilterT2 ;
    TextView  FilterT3;
    TextView  FilterT4;
    EditText CCN;
    EditText Type ;
    EditText FDate ;
    EditText FTime;
    ScrollView SCRLView;
    Button Button2;
    Button Button3;
    Button Button4;
    Button Button5;
    Button Button6;
    Button Button7;
    Button Button8;
    Button Button9;
    Button Button10;
    Button Button1;

    int filterenable;

    public FirebaseDatabase dbMain = FirebaseDatabase.getInstance();
    private  String regno = "";
    public DatabaseReference dEvents = dbMain.getReference("Events");

    public void upButtonenable(@NonNull Button b){

        Button2.setBackgroundResource(R.drawable.button_filterdesel);
        Button1.setBackgroundResource(R.drawable.button_filterdesel);
        Button3.setBackgroundResource(R.drawable.button_filterdesel);
        Button4.setBackgroundResource(R.drawable.button_filterdesel);
        Button5.setBackgroundResource(R.drawable.button_filterdesel);
        Button6.setBackgroundResource(R.drawable.button_filterdesel);
        Button7.setBackgroundResource(R.drawable.button_filterdesel);
        Button8.setBackgroundResource(R.drawable.button_filterdesel);
        Button9.setBackgroundResource(R.drawable.button_filterdesel);
        Button10.setBackgroundResource(R.drawable.button_filterdesel);


        b.setBackgroundResource(R.drawable.button_filtersel);
        if(b.getText().toString().equalsIgnoreCase("all")){
            filterClub("all");
        }else{
            filterClub(b.getText().toString());
        }
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

        setContentView(R.layout.activity_event);

        layout = findViewById(R.id.cardContainer);
        ImageView Back = findViewById(R.id.imageView40);
        SCRLView = findViewById(R.id.scrollView2);
        Button2 = findViewById(R.id.button2);
        Button1 = findViewById(R.id.button1);
        Button3 = findViewById(R.id.button3);
        Button4 = findViewById(R.id.button4);
        Button5 = findViewById(R.id.button5);
        Button6 = findViewById(R.id.button6);
        Button7 = findViewById(R.id.button7);
        Button8 = findViewById(R.id.button8);
        Button9 = findViewById(R.id.button9);
        Button10 = findViewById(R.id.button10);

        getmailandregno GMA = new getmailandregno();
        getmailandregno.Callback Callback = new getmailandregno.Callback(){
            @Override
            public void onDataReceived() {
                regno = GMA.getRegisternumber();
                filterClub("all");
            }
        };
        GMA.getValues(Callback);

        Back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        Button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                upButtonenable(Button1);
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
        Button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                upButtonenable(Button4);
            }
        });
        Button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                upButtonenable(Button5);
            }
        });
        Button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                upButtonenable(Button6);
            }
        });
        Button7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                upButtonenable(Button7);
            }
        });
        Button8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                upButtonenable(Button8);
            }
        });
        Button9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                upButtonenable(Button9);
            }
        });
        Button10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                upButtonenable(Button10);
            }
        });


    }

    private void filterClub(String type){
        dEvents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                layout.removeAllViews();
                for (DataSnapshot EventSnapShot : dataSnapshot.getChildren()) {
                    String EventID = EventSnapShot.getKey();
                    Event EventOBJ = EventSnapShot.getValue(Event.class);
                    String dateEvent = EventOBJ.getDate();
                    String EventType = EventOBJ.getType();
                    if(type.equalsIgnoreCase("all")) {
                        if (compare_Dates(dateEvent) <= 0) {
                            View view = getLayoutInflater().inflate(R.layout.cardevent, null);
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

                            Event_ID.setText("Event ID: " + EventID);
                            EventName.setText(EventOBJ.getName());
                            ClubName.setText(EventOBJ.getCC());
                            Desc.setText(EventOBJ.getDescription());
                            Venue.setText("Venue: " + EventOBJ.getVenue());
                            Event_date.setText("Date: " + EventOBJ.getDate());
                            Event_Time.setText("Time: " + EventOBJ.getStartTime() + " - " + EventOBJ.getEndTime());
                            if (EventOBJ.getMaxSeats() != 0) {
                                if (EventOBJ.getRegisteredUsers().contains(regno)) {
                                    display_eventcard_button(ButtonReg, ButtonFilled, ButtonRegistered, ButtonRegistered);
                                } else {
                                    display_eventcard_button(ButtonReg, ButtonFilled, ButtonRegistered, ButtonReg);
                                }
                            } else {
                                display_eventcard_button(ButtonReg, ButtonFilled, ButtonRegistered, ButtonFilled);
                            }

                            layout.addView(view);
                        }
                    }else{
                        if(EventType.equalsIgnoreCase(type)) {
                            if (compare_Dates(dateEvent) <= 0) {
                                View view = getLayoutInflater().inflate(R.layout.cardevent, null);
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

                                Event_ID.setText("Event ID: " + EventID);
                                EventName.setText(EventOBJ.getName());
                                ClubName.setText(EventOBJ.getCC());
                                Desc.setText(EventOBJ.getDescription());
                                Venue.setText("Venue: " + EventOBJ.getVenue());
                                Event_date.setText("Date: " + EventOBJ.getDate());
                                Event_Time.setText("Time: " + EventOBJ.getStartTime() + " - " + EventOBJ.getEndTime());
                                if (EventOBJ.getMaxSeats() != 0) {
                                    if (EventOBJ.getRegisteredUsers().contains(regno)) {
                                        display_eventcard_button(ButtonReg, ButtonFilled, ButtonRegistered, ButtonRegistered);
                                    } else {
                                        display_eventcard_button(ButtonReg, ButtonFilled, ButtonRegistered, ButtonReg);
                                    }
                                } else {
                                    display_eventcard_button(ButtonReg, ButtonFilled, ButtonRegistered, ButtonFilled);
                                }

                                layout.addView(view);
                            }
                        }

                    }
                }
                for (int i = 0; i < layout.getChildCount(); i++) {
                    View cardView = layout.getChildAt(i);
                    ImageView regButton = cardView.findViewById(R.id.imageView31);

                    regButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TextView textView = cardView.findViewById(R.id.textView56);
                            String cardText = textView.getText().toString();
                            dEvents.child(cardText.substring(10)).child("RegisteredUsers").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String currentValue = dataSnapshot.getValue(String.class);
                                    String newValue = currentValue + ","+regno;
                                    updateRegisteredUsers(dEvents.child(cardText.substring(10)), newValue);
                                    dEvents.child(cardText.substring(10)).child("MaxSeats").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            Long currentValue = dataSnapshot.getValue(Long.class);
                                            Long newValue = currentValue-1;
                                            updateMaxSeats(dEvents.child(cardText.substring(10)), newValue);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            System.out.println("Error In Database: "+ error.toException());
                                        }
                                    });
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    System.out.println("Error In Database: "+ error.toException());
                                }
                            });
                        }
                    });
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

    private void display_eventcard_button(ImageView a,ImageView b,ImageView c,ImageView d){
        a.setVisibility(View.INVISIBLE);
        b.setVisibility(View.INVISIBLE);
        c.setVisibility(View.INVISIBLE);
        d.setVisibility(View.VISIBLE);
    }

    private void updateRegisteredUsers(DatabaseReference eventRef, String newValue) {
        // Use updateChildren to update the specific child
        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("RegisteredUsers", newValue);

        eventRef.updateChildren(updateMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Update successful
                        } else {
                            Exception exception = task.getException();
                            if (exception != null) {
                                exception.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void updateMaxSeats(DatabaseReference eventRef, Long newValue) {
        // Use updateChildren to update the specific child
        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("MaxSeats", newValue);

        eventRef.updateChildren(updateMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Update successful
                        } else {
                            Exception exception = task.getException();
                            if (exception != null) {
                                exception.printStackTrace();
                            }
                        }
                    }
                });
    }
}
