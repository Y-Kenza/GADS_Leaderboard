package com.kenzadev.gads_leaderboard.models;

public class SkillIQModel implements Comparable<SkillIQModel> {
    private String name;
    private int score;
    private String country;
    private String badgeUrl;

    public SkillIQModel(String name, int score, String country, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
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

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    @Override
    public int compareTo(SkillIQModel skillIQModel) {
        if (this.getScore()>skillIQModel.getScore())
        {
            return 1;
        }
        else if (this.getScore()<skillIQModel.getScore())
        {
            return -1;
        }
        return 0;
    }
}
