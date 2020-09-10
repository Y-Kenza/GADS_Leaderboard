package com.kenzadev.gads_leaderboard.models;

public class LearningLeaderModel implements Comparable<LearningLeaderModel> {
    private String name;
    private int hours;
    private String country;
    private String badgeUrl;

    public LearningLeaderModel(String name, int hours, String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
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

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    @Override
    public int compareTo(LearningLeaderModel learningLeaderModel) {
        if (this.getHours()>learningLeaderModel.getHours())
        {
            return 1;
        }
        else if (this.getHours()<learningLeaderModel.getHours())
        {
            return -1;
        }
        return 0;
    }
}

