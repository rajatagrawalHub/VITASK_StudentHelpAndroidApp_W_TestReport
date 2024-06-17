package com.base.ProjectTestAndroid;


public class Task {
    private String Date;
    private String Description;
    private String EndTime;
    private String Name;
    private String RegisteredUsers;
    private String StartTime;
    private String Venue;

    public Task(){ }

   public Task(String b, String c, String d, String f, String g, String h, String i, String j){
        Date = b;
        Description = c;
        EndTime = d;
        Name = f;
        RegisteredUsers = g;
        StartTime = h;
        Venue = j;
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

    public String getName() {
        return Name;
    }

    public String getRegisteredUsers() {
        return RegisteredUsers;
    }

    public String getStartTime() {
        return StartTime;
    }

    public String getVenue() {
        return Venue;
    }

    public void setRegisteredUsers(String registeredUsers) {
        RegisteredUsers = registeredUsers;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }
}
