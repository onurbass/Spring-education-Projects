package com.onurbas.annotation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component

public class Pacman implements IRunable {

    public void up(){
        System.out.println("Yukarı");
    }
    public void down(){
        System.out.println("Asagı");
    }
    public void left(){
        System.out.println("Sola");
    }
    public void  right(){
        System.out.println("Sağa");
    }
}
