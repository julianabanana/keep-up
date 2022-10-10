package com.example.trackingforgym.data;

public class Session {
    private static Boolean session;
    private static User user;

    public static Boolean getSession(){
        return session;
    }
    public static User getUser(){
        return user;
    }

    public static void setSession(Boolean i){
        session=i;
    }

    public static void setUser(User i){
        user=i;
    }

    public Session(User i){
        session=true;
        user =i;
    }

    public Session(){
        session=false;
    }
}
