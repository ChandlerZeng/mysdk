package com.zcq.sdk.test.entity;

public class PhoneBean {
    private String name;
    private String number;
    private String status;
    private String time;

    public PhoneBean(String name,String number,String status,String time){
        this.name = name;
        this.number = number;
        this.status = status;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
