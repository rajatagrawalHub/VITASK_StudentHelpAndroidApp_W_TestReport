package com.base.ProjectTestAndroid;



import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTask {
    public static boolean verify_description(String Desc_Length){
        if(Desc_Length.length()>50){
            showAlertDialog("Invalid Descripton","Description Should be 40 letters");
            return false;
        }else{
            return true;
        }
    }

    private static void showAlertDialog(String invalidDescripton, String s) {
    }



    public static boolean verify_Time(String timeEntered){
        try{
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date TodayTime = timeFormat.parse(timeEntered);
            return true;
        }catch(Exception e){

            return false;
        }
    }
    public static boolean verify_Date(String dateEntered){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date TodayDate = dateFormat.parse(dateEntered);
            return true;
        }catch(Exception e){


            return false;
        }
    }

}
