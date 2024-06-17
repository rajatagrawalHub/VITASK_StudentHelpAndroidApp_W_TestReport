package com.base.ProjectTestAndroid;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.Button;
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
import com.squareup.picasso.Picasso;

public class ChapterActivity extends AppCompatActivity {

    LinearLayout layout;
    Button Button2 ;
    Button Button3;
    Button Button5 ;
    Button Button7;
    public FirebaseDatabase dbMain = FirebaseDatabase.getInstance();
    public DatabaseReference dDetailCC = dbMain.getReference("DetailCC");


    public void upButtonenable(Button b){

        Button2.setBackgroundResource(R.drawable.button_filterdesel);
        Button3.setBackgroundResource(R.drawable.button_filterdesel);
        Button5.setBackgroundResource(R.drawable.button_filterdesel);
        Button7.setBackgroundResource(R.drawable.button_filterdesel);

        b.setBackgroundResource(R.drawable.button_filtersel);

        if(b.getText().toString().equalsIgnoreCase("all")){
            display_all();
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

        setContentView(R.layout.activity_chapter);


        layout = findViewById(R.id.cardContainer);
        ImageView Back = findViewById(R.id.imageView40);
        Button2 = findViewById(R.id.button2);
        Button3 = findViewById(R.id.button3);
        Button5 = findViewById(R.id.button5);
        Button7 = findViewById(R.id.button7);

        View view = getLayoutInflater().inflate(R.layout.cardchapter,null);
        layout.addView(view);

        display_all();

        Back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChapterActivity.this, CCSelectionmenu.class);
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

        Button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                upButtonenable(Button5);
            }
        });

        Button7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                upButtonenable(Button7);
            }
        });


    }

    private void filterClub(String filterString){
        layout.removeAllViews();
        dDetailCC.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot CCSnapshot : dataSnapshot.getChildren()) {
                    String CCname = CCSnapshot.getKey();
                    CC CCObj = CCSnapshot.getValue(CC.class);
                    if(CCObj.getType().equalsIgnoreCase("Chapter")){
                        if(CCObj.getCategory().equalsIgnoreCase(filterString)) {
                            View view = getLayoutInflater().inflate(R.layout.cardchapter, null);
                            TextView Name = view.findViewById(R.id.textView36);
                            TextView Desc = view.findViewById(R.id.textView38);
                            ImageView logo = view.findViewById(R.id.imageView22);

                            Name.setText(CCname);
                            Desc.setText(CCObj.getDescription());
                            Picasso.get().load(CCObj.getLogoURL()).into(logo);

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

    private void display_all(){
        layout.removeAllViews();
        dDetailCC.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot CCSnapshot : dataSnapshot.getChildren()) {
                    String CCname = CCSnapshot.getKey();
                    CC CCObj = CCSnapshot.getValue(CC.class);
                    if(CCObj.getType().equalsIgnoreCase("Chapter")){
                        View view = getLayoutInflater().inflate(R.layout.cardchapter,null);
                        TextView Name = view.findViewById(R.id.textView36);
                        TextView Desc = view.findViewById(R.id.textView38);
                        ImageView logo = view.findViewById(R.id.imageView22);

                        Name.setText(CCname);
                        Desc.setText(CCObj.getDescription());
                        Picasso.get().load(CCObj.getLogoURL()).into(logo);

                        layout.addView(view);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Error In Database: "+ error.toException());
            }
        });

    }

}
