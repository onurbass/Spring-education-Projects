package com.onurbas.annotation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Mario implements IRunable {
    public void up(){
        System.out.println("Zıpla");
    }
    public void down(){
        System.out.println("Bir deliğe gir");
    }
    public void left(){
        System.out.println("Geri don");
    }
    public void  right(){
        System.out.println("Hızlan");
    }
}
