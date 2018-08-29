package com.example.hp.vf;

import java.util.Comparator;

/**
 * Created by suraj on 21-07-2018.
 */
public class Gst implements Comparable<Gst>{
    private String id,gst_name,gst_number,mobile,type,balance;

    public Gst(String id,String gst_name,String gst_number,String mobile,String type,String balance){
        this.setId(id);
        this.setGst_name(gst_name);
        this.setGst_number(gst_number);
        this.setMobile(mobile);
        this.setType(type);
        this.setBalance(balance);
    }
    public String getId() {
        return id;
    }



    public String getGst_name() {
        return gst_name;
    }

    public String getGst_number() {
        return gst_number;
    }

    public String getMobile() {
        return mobile;
    }

    public String getType() {
        return type;
    }

    public String getBalance() {
        return balance;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGst_name(String gst_name) {
        this.gst_name = gst_name;
    }

    public void setGst_number(String gst_number) {
        this.gst_number = gst_number;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public int compareTo(Gst another) {
        return getGst_name().compareTo(another.getGst_name());
    }
}
