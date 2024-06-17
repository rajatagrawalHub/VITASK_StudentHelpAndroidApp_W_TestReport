package com.base.ProjectTestAndroid;


public class Event {
    private String CC;
    private String Date;
    private String Description;
    private String EndTime;
    private Long MaxSeats;
    private String Name;
    private String RegisteredUsers;
    private String StartTime;
    private String Type;
    private String Venue;



    public Event(){

    }

   public Event(String a,String b,String c,String d,Long e,String f,String g,String h,String i,String j){
        CC = a;
        Date = b;
        Description = c;
        EndTime = d;
        MaxSeats = e;
        Name = f;
        RegisteredUsers = g;
        StartTime = h;
        Type = i;
        Venue = j;
   }

    public String getCC() {
        return CC;
    }

    public String getDate() {
        return Date;
    }

    public String getDescription() {
        return Description;
    }

    public String getEndTime() {
        return EndTime;
    }

    public Long getMaxSeats() {
        return MaxSeats;
    }

    public String getName() {
        return Name;
    }

    public String getRegisteredUsers() {
        return RegisteredUsers;
    }

    public String getStartTime() {
        return StartTime;
    }

    public String getType() {
        return Type;
    }

    public String getVenue() {
        return Venue;
    }

}
