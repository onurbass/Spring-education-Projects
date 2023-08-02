package com.onurbas.springcoredemo.rest;

import com.onurbas.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

//Component altına yazılan Primary anotasyonu birden fazla classa yazılamaz ve qualifierın işini yapar
    //Qualifier eklemek daha spesifik bir çözüm getirir.
// Hangi class olduğunu önemsemediğimizde bu örnekde hangi coach olduğu gibi primary kullanılabilir
    //Qualifier daha yüksek önceliğe sahiptir*** 2sinide kullandığında yine qualifier öncelik alacaktır
    private Coach myCoach;

    @Autowired
    public  DemoController(Coach coach){
        this.myCoach = coach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWork();
    }
}
