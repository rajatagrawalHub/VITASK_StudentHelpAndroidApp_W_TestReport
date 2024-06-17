package com.base.ProjectTestAndroid;


import androidx.appcompat.app.AppCompatActivity;
import android.view.*;
import android.content.Intent;
import android.widget.*;
import android.os.Bundle;

public class DevelopmentStageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        WindowInsetsController insetsController = decorView.getWindowInsetsController();

        if (insetsController != null) {
            insetsController.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars() | WindowInsets.Type.systemBars());
            insetsController.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }

        setContentView(R.layout.activity_developmentstage);

        ImageView HomeButton = findViewById(R.id.imageView15);

        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DevelopmentStageActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}