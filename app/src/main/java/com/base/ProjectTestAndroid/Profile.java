package com.base.ProjectTestAndroid;


public class Profile {
    private String Name;
    private String mailID;

    public Profile(){

    }

    public Profile(String name, String mailid){
        Name = name;
        mailID = mailid;
    }

    public String getName(){
        return Name;
    }

    public String getmailId(){
        return mailID;
    }

}
