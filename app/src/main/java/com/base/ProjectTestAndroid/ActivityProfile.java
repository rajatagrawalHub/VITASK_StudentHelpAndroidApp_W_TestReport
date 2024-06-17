package com.base.ProjectTestAndroid;

import android.content.*;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.os.Build;
import android.app.*;

import androidx.annotation.NonNull;
import androidx.core.app.*;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class ActivityProfile extends AppCompatActivity {

    public FirebaseDatabase dbMain = FirebaseDatabase.getInstance();
    public DatabaseReference dDetailCC = dbMain.getReference("DetailCC");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        WindowInsetsController insetsController = decorView.getWindowInsetsController();

        if (insetsController != null) {
            insetsController.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars() | WindowInsets.Type.systemBars());
            insetsController.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }

        setContentView(R.layout.activity_profile);

        ImageView AccountButton = findViewById(R.id.imageView16);
        ImageView CCButton = findViewById(R.id.imageView17);
        ImageView EventButton = findViewById(R.id.imageView19);
        ImageView AddButton = findViewById(R.id.imageView18);
        ImageView HomeButton = findViewById(R.id.imageView15);
        Button MyEventB = findViewById(R.id.buttonmyevent);
        TextView SignOut = findViewById(R.id.textView50);

        getmailandregno GMA = new getmailandregno();

        getmailandregno.Callback Callback = new getmailandregno.Callback(){
            @Override
            public void onDataReceived() {
                TextView nameField = findViewById(R.id.textView39);
                TextView regField = findViewById(R.id.textView41);
                nameField.setText(GMA.getName());
                String Regno = GMA.getRegisternumber();
                regField.setText(Regno);
                get_registered_Clubs(Regno);
            }
        };

        GMA.getValues(Callback);


        AccountButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityProfile.this, ActivityProfile.class);
                startActivity(intent);
            }
        });

        SignOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityProfile.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        CCButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityProfile.this, CCSelectionmenu.class);
                startActivity(intent);
            }
        });

        EventButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityProfile.this, EventActivity.class);
                startActivity(intent);
            }
        });

        AddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityProfile.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityProfile.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        MyEventB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityProfile.this, MyEvents.class);
                startActivity(intent);
            }
        });
    }

    private void get_registered_Clubs(String Reg_No){
        dDetailCC.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot CCSnapshot : dataSnapshot.getChildren()) {
                    String CCname = CCSnapshot.getKey();
                    CC CCObj = CCSnapshot.getValue(CC.class);
                    if(CCObj.getRegisteredUsers().contains(Reg_No)){
                       String Name = CCname;
                       String Type = CCObj.getType().toUpperCase();
                       LinearLayout linearlayoutCC = findViewById(R.id.linearlayoutCC);
                       LinearLayout containerBody = new LinearLayout(ActivityProfile.this);
                       LinearLayout.LayoutParams LLP =  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                       float scale = getResources().getDisplayMetrics().density;
                       int topMargin = (int) (10 * scale + 0.5f);
                       LLP.setMargins(0,topMargin,0,0);
                       containerBody.setLayoutParams(LLP);
                       linearlayoutCC.addView(containerBody);
                       TextView CCNameView = new TextView(ActivityProfile.this);
                       CCNameView.setText(Name);
                       CCNameView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                       CCNameView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,12);
                       CCNameView.setTextColor(Color.parseColor(return_color(Type)));
                       CCNameView.setTypeface(null,Typeface.BOLD);
                       CCNameView.setAllCaps(true);
                       containerBody.addView(CCNameView);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Error In Database: "+ error.toException());
            }
        });
    }

    private String return_color(String Type){
        if(Type.equalsIgnoreCase("club")){
            return "#A90084";
        }else{
            return "#450738";
        }
    }

}
