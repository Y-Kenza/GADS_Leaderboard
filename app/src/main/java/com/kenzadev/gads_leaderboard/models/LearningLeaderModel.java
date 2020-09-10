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


    public int getHours() {
        return hours;
    }



    public String getCountry() {
        return country;
    }



    public String getBadgeUrl() {
        return badgeUrl;
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

