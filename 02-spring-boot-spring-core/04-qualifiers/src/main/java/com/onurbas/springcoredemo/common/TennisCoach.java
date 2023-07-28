package com.onurbas.springcoredemo.common;


import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach{
    @Override
    public String getDailyWork() {
        return "Spend your backhand volley";
    }
}
