package com.zikozee.skill;

public class Skill {

    private String name;
    private int score;
    private String country;
    private String url;

    public Skill() {
    }

    public Skill(String name, int score, String country, String url) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
