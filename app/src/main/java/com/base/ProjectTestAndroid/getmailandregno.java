package com.base.ProjectTestAndroid;


import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class getmailandregno {

    public FirebaseDatabase dbMain = FirebaseDatabase.getInstance();
    public DatabaseReference dProfile = dbMain.getReference("Profile");
    public DatabaseReference dOTP = dbMain.getReference("OTP");
    private String EmailEntered;
    private String otp_generated;
    private static String registernumber;
    private static String Name;

    public interface Callback {
        void onDataReceived();
    }

    public void getValues(final Callback callback){
        dOTP.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot OTPSnapShot : dataSnapshot.getChildren()) {
                    otp_generated = OTPSnapShot.getKey();
                }
                dOTP.child(otp_generated).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                           EmailEntered = dataSnapshot.getValue(String.class);
                        }
                        dProfile.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot ProfileSnapShot : dataSnapshot.getChildren()) {
                                    String registerNumber = ProfileSnapShot.getKey();
                                    String mailID = ProfileSnapShot.child("mailID").getValue(String.class);
                                    if(mailID.equalsIgnoreCase(EmailEntered)) {
                                        Profile ProfileOBJ = ProfileSnapShot.getValue(Profile.class);
                                        Name = ProfileOBJ.getName();
                                        registernumber = registerNumber;
                                    }else{
                                        System.out.println("No Such Mail Found");
                                    }
                                }
                                callback.onDataReceived();
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Error In Database: "+ error.toException());
            }
        });

    }

    public static String getName() {
        return Name;
    }

    public  String getMailID() {
        return EmailEntered;
    }

    public  String getOtp_generated() {
        return otp_generated;
    }

    public static String getRegisternumber() {
        return registernumber;
    }
}
