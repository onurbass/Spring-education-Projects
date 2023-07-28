package com.onurbas.springcoredemo;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{

    @Override
    public String getDailyWork() {
        return "Practise fast bowling for 15 minustes";
    }
}
