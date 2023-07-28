package com.onurbas.springcoredemo.common;


import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach{
    @Override
    public String getDailyWork() {
        return "Run a hard 5k";
    }
}
