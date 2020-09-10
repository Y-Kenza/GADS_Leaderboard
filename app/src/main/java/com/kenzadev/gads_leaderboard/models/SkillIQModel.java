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

    public int getScore() {
        return score;
    }

    public String getCountry() {
        return country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
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
