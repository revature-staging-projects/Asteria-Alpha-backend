package com.revature.models;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class MarsWeather {
    int sol;

    LocalDate stamp;

    String season;

    float AT;

    float PRE;

    public MarsWeather() {
        super();
    }


    public int getSol() {
        return sol;
    }

    public void setSol(final int sol) {
        this.sol = sol;
    }

    public LocalDate getStamp() {
        return stamp;
    }

    public void setStamp(final LocalDate stamp) {
        this.stamp = stamp;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(final String season) {
        this.season = season;
    }

    public float getAT() {
        return AT;
    }

    public void setAT(final float AT) {
        this.AT = AT;
    }

    public float getPRE() {
        return PRE;
    }

    public void setPRE(final float PRE) {
        this.PRE = PRE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarsWeather that = (MarsWeather) o;
        return sol == that.sol && Float.compare(that.AT, AT) == 0
                && Float.compare(that.PRE, PRE) == 0
                && stamp.equals(that.stamp)
                && season.equals(that.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sol, stamp, season, AT, PRE);
    }

    @Override
    public String toString() {
        return "MarsWeather{" +
                "sol=" + sol +
                ", stamp=" + stamp +
                ", season='" + season + '\'' +
                ", AT=" + AT +
                ", PRE=" + PRE +
                '}';
    }
}
