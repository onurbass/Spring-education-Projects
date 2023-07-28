package com.onurbas.springcoredemo.rest;

import com.onurbas.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    //dependency için bir private field tanımla
    private Coach myCoach;
//setter injection
    @Autowired //setCoach yerine istediğin ismi verebilirdin önemli değil
    public void setCoach(Coach coach){
        this.myCoach = coach;
    }
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWork();
    }
}
