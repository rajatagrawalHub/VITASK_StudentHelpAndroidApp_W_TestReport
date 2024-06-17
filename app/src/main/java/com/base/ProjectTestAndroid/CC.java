package com.base.ProjectTestAndroid;


public class CC {
    private String Category;
    private String Description;
    private String LogoURL;
    private String Type;
    private String RegisteredUsers;

    private String mailID;

    public CC(){

    }

    public CC(String cat, String desc, String logo, String type){
        Category = cat;
        Description = desc;
        LogoURL = logo;
        Type = type;
    }

    public String getCategory(){
        return Category;
    }

    public String getDescription(){
        return Description;
    }
    public String getLogoURL(){return LogoURL;}

    public String getType(){return Type;}

    public String getRegisteredUsers() {
        return RegisteredUsers;
    }

    public String getMailID() {
        return mailID;
    }
}
