package com.onurbas.springcoredemo.rest;

import com.onurbas.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

//Autowired 'ın görebileceği birden fazla class olduğunda @Qualifier eklenir.Böylece hangisinin tercih edildiği belirtilerek hata önlenir***
    //aşağıdaki örnekte constructor injection içine bi @Qualifier eklendi ve baseballCoach camel case olarak belirtildi
    private Coach myCoach;

    @Autowired
    public  DemoController(@Qualifier("baseballCoach") Coach coach){
        this.myCoach = coach;
    }
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWork();
    }
}
