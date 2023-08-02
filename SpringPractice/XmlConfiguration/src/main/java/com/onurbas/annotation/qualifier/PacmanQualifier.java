package com.onurbas.annotation.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("pacman")
public class PacmanQualifier implements IRunableQualifier {

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
