package com.base.ProjectTestAndroid;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;
import java.security.SecureRandom;


import androidx.annotation.NonNull;
import androidx.core.app.*;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.TestOnly;


public class LoginActivity extends AppCompatActivity {

    public String OTP_CHARACTERS = "0123456789";
    private String mailEntered = "";

    public FirebaseDatabase dbMain = FirebaseDatabase.getInstance();
    public DatabaseReference dOTP = dbMain.getReference("OTP");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        WindowInsetsController insetsController = decorView.getWindowInsetsController();

        if (insetsController != null) {
            insetsController.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars() | WindowInsets.Type.systemBars());
            insetsController.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }

        setContentView(R.layout.activity_login);

        Button getOtp = findViewById(R.id.buttonGetOtp);
        TextView emailTextBox = findViewById(R.id.editTextText);

        getOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailEntered = emailTextBox.getText().toString();
                verifyEmail(emailTextBox.getText().toString());
                emailTextBox.setText(null);
            }
        });

        dOTP.removeValue().addOnCompleteListener(new OnCompleteListener <Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()) {
                    Exception exception = task.getException();
                    if (exception != null) {
                        exception.printStackTrace();
                    }
                }
            }
        });
    }

   public  String generate_OTP(){
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int index = secureRandom.nextInt(OTP_CHARACTERS.length());
            otp.append(OTP_CHARACTERS.charAt(index));
        }
        return otp.toString();
    }

    private String otp_generated="";
    private void verifyEmail(String mail){
        if(mail.contains("@")) {
            String mailPart[] = mail.split("@");
            if (!mailPart[1].equalsIgnoreCase("vitstudent.ac.in")) {
                showAlertDialog("Invalid Mail", "Please Enter Your VIT mail.");
            }else {
                otp_generated = generate_OTP();
                EmailSender MailSendObj = new EmailSender(mail,otp_generated);
                try {
                    MailSendObj.sendOTPEmail();
                }catch(Exception e){
                    showAlertDialog("Error", "Retry OTP generation");
                    return;
                }
                dOTP.child(otp_generated).setValue(mailEntered.toLowerCase());
                Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
                startActivity(intent);
            }
        }else{
            showAlertDialog("Invalid Mail", "Please Enter Your Mail");
        }
    }

    // Assuming dOTP is your DatabaseReference



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
