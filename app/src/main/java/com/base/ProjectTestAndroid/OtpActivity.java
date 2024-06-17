package com.base.ProjectTestAndroid;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OtpActivity extends AppCompatActivity {
    public FirebaseDatabase dbMain = FirebaseDatabase.getInstance();
    public DatabaseReference dOTP = dbMain.getReference("OTP");
    public static String otp_generated="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        WindowInsetsController insetsController = decorView.getWindowInsetsController();

        if (insetsController != null) {
            insetsController.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars() | WindowInsets.Type.systemBars());
            insetsController.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }

        setContentView(R.layout.activity_otp);

        EditText et1 = findViewById(R.id.edit1);
        EditText et2 = findViewById(R.id.edit2);
        EditText et3 = findViewById(R.id.edit3);
        EditText et4 = findViewById(R.id.edit4);
        Button submit = findViewById(R.id.buttonSubmit);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                et2.requestFocus();
            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                et3.requestFocus();
            }
        });

        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                et4.requestFocus();
            }
        });

        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                inputMethodManager.hideSoftInputFromWindow(et4.getWindowToken(), 0);
                et4.clearFocus();
            }
        });

        et1.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_NEXT){
                et2.requestFocus();
                return true;
            }
            return  false;
        });

        et2.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_NEXT){
                et3.requestFocus();
                return true;
            }
            return  false;
        });

        et3.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_NEXT){
                et4.requestFocus();
                return true;
            }
            return  false;
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otpEntered = et1.getText().toString()+et2.getText().toString()+et3.getText().toString()+et4.getText().toString() ;

                if(!otp_generated.equals(otpEntered)){
                    showAlertDialog("Invalid OTP","Please Enter the correct OTP");
                }else {
                    Intent intent = new Intent(OtpActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });

        dOTP.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot OTPSnapShot : dataSnapshot.getChildren()) {
                    otp_generated = OTPSnapShot.getKey();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Error In Database: "+ error.toException());
            }
        });

    }

    public static String return_generatedOTP(){
        return otp_generated;
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
