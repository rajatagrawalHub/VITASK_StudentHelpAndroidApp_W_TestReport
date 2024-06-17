package com.base.ProjectTestAndroid;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CCSelectionmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        WindowInsetsController insetsController = decorView.getWindowInsetsController();

        if (insetsController != null) {
            insetsController.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars() | WindowInsets.Type.systemBars());
            insetsController.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }

        setContentView(R.layout.activity_ccselection);

        ImageView AccountButton = findViewById(R.id.imageView16);
        ImageView CCButton = findViewById(R.id.imageView17);
        ImageView EventButton = findViewById(R.id.imageView19);
        ImageView AddButton = findViewById(R.id.imageView18);
        ImageView HomeButton = findViewById(R.id.imageView15);
        ImageView ClubButton = findViewById(R.id.imageView24);
        ImageView ChapterButton = findViewById(R.id.imageView25);


        AccountButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CCSelectionmenu.this, ActivityProfile.class);
                startActivity(intent);
            }
        });

        CCButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CCSelectionmenu.this, CCSelectionmenu.class);
                startActivity(intent);
            }
        });

        EventButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CCSelectionmenu.this, EventActivity.class);
                startActivity(intent);
            }
        });

        AddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CCSelectionmenu.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CCSelectionmenu.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        ClubButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CCSelectionmenu.this, ClubActivity.class);
                startActivity(intent);
            }
        });

        ChapterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CCSelectionmenu.this, ChapterActivity.class);
                startActivity(intent);
            }
        });



    }
}
