package com.zikozee.learning;

public class Learning {

    private String name;
    private int hours;
    private String country;
    private String url;

    public Learning() {
    }

    public Learning(String name, int hours, String country, String url) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
